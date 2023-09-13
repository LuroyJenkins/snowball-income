package org.starkov.tests.api;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.starkov.helpers.VarargsAggregator;
import org.starkov.tests.api.dataProviders.AddWatchlistProvider;
import org.starkov.tests.api.domain.Asset;
import org.starkov.tests.api.models.portfolio.PortfolioModel;
import org.starkov.tests.api.models.user.GetPublicRespModel;
import org.starkov.tests.api.models.user.PublicInvestorLinks;
import org.starkov.tests.api.models.watchlist.WatchlistListModel;
import org.starkov.tests.api.models.watchlist.WatchlistValueModel;
import org.starkov.tests.api.requests.PortfolioApi;
import org.starkov.tests.api.requests.UserApi;
import org.starkov.tests.api.requests.WatchlistApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("API")
public class ApiTests {
    PortfolioApi portfolioApi = new PortfolioApi();
    WatchlistApi watchlistApi = new WatchlistApi();
    UserApi userApi = new UserApi();

    @Test
    @Story("Демо портфель")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка наличия стандартного демо-портфеля у пользователя")
    public void getPortfolioTest() {
        PortfolioModel[] portfolio = step("Выполняем запрос портфелей пользователя",
                () -> portfolioApi.getMyPortfolios());
        step("Проверяем, что имя портфеля совпадает",
                () -> assertEquals("Демо портфель", portfolio[0].getName()));
        step("Проверяем, что сумма портфеля совпадает",
                () -> assertEquals(1000000.0, portfolio[0].getGoalValue()));
        step("Проверяем, что валюта портфеля совпадает",
                () -> assertEquals("USD", portfolio[0].getGoalCurrency()));
    }

    @Test
    @Story("Стандартный список избранного")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка наличия стандартного списка избранного")
    public void getWatchlistTest() {
        WatchlistListModel[] watchlist = step("Выполняем запрос листов избранного",
                () -> watchlistApi.getWatchlistList());
        step("Проверяем, что имя списка совпадает",
                () -> assertEquals("Избранное", watchlist[0].getName()));
        step("Проверяем, что заметка к списку совпадает",
                () -> assertEquals("Стандартный список", watchlist[0].getNote()));
        step("Проверяем, что количество отслеживаемого совпадает",
                () -> assertEquals(0, watchlist[0].getAssetsCount()));
    }

    @ParameterizedTest
    @Story("Списки отслеживания")
    @Severity(SeverityLevel.CRITICAL)
    @ArgumentsSource(AddWatchlistProvider.class)
    @DisplayName("Добавление актива в избранное")
    public void addWatchTest(@AggregateWith(VarargsAggregator.class) Asset... assets) {
        List<String> assetsValuesId = new ArrayList<>();

        WatchlistListModel[] watchList = step("Получение списков избранных активов",
                () -> watchlistApi.getWatchlistList());

        WatchlistListModel[] finalWatchList = watchList;

        step("Добавление актива в стандартный список избранного", () -> {
            for (int i = 0; i < assets.length; i++) {
                watchlistApi.addValueForWatch(assets[i].getTicker(), assets[i].getExchange(), assets[i].getCurrency(),
                        finalWatchList[0].getId());
                WatchlistValueModel[] values = watchlistApi.getWatchlistValues(finalWatchList[0].getId(), "1m");
                assertEquals(assets[i].getTicker(), values[i].getTicker());
                assertEquals(assets[i].getCurrency(), values[i].getCurrency());
                assetsValuesId.add(values[i].getId());
            }
        });
        watchList = step("Получение обновленного списка активов в избранном",
                () -> watchlistApi.getWatchlistList());

        assertEquals(assets.length, watchList[0].getAssetsCount());

        for (String id : assetsValuesId) {
            step("Удаление актива из списка",
                    () -> watchlistApi.delValueFromWatchlist(id));
        }
    }

    @Test
    @Story("Списки отслеживания")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление актива из избранного")
    public void deleteWatchTest() {
        WatchlistListModel[] watchList = watchlistApi.getWatchlistList();
        watchlistApi.addValueForWatch("AAPL", "US", "USD", watchList[0].getId());
        watchList = watchlistApi.getWatchlistList();
        assertEquals(1, watchList[0].getAssetsCount());
        WatchlistValueModel[] watchListValues = watchlistApi.getWatchlistValues(watchList[0].getId(), "1m");
        watchlistApi.delValueFromWatchlist(watchListValues[0].getId());
        watchList = watchlistApi.getWatchlistList();
        assertEquals(0, watchList[0].getAssetsCount());
    }

    static Stream<Arguments> addPublicLinksTest() {
        return Stream.of(
                Arguments.of("Test1", Map.of("PublicInvestorLinks[0].Name", "VK",
                        "PublicInvestorLinks[0].Url", "https://vk.com/")),
                Arguments.of("Test22132121313", Map.of("PublicInvestorLinks[0].Url", "https://twitter.com/",
                        "PublicInvestorLinks[0].Name", "Twitter",
                        "PublicInvestorLinks[1].Name", "VK",
                        "PublicInvestorLinks[1].Url", "https://vk.com/")),
                Arguments.of("___Test___", Map.of(
                        "PublicInvestorLinks[0].Name", "VK",
                        "PublicInvestorLinks[0].Url", "https://vk.com/",
                        "PublicInvestorLinks[1].Name", "YouTube",
                        "PublicInvestorLinks[1].Url", "https://youtube.com/",
                        "PublicInvestorLinks[2].Name", "Twitter",
                        "PublicInvestorLinks[2].Url", "https://twitter.com/")
                )
        );
    }

    @ParameterizedTest
    @Story("Публичный профиль")
    @Severity(SeverityLevel.NORMAL)
    @MethodSource("addPublicLinksTest")
    @DisplayName("Добавление ссылки на соц.сети")
    public void addPublicLinksTest(String investorName, Map<String, String> links) {
        userApi.addPublicInfo(investorName, links);

        GetPublicRespModel publicProfile = userApi.getPublicInfo();
        List<String> values = new ArrayList<>(links.values());

        for (PublicInvestorLinks link : publicProfile.getPublicInvestorLinks()) {
            assertTrue(values.contains(link.getName()));
            assertTrue(values.contains(link.getUrl()));
        }
    }
}