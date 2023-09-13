package org.starkov.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.starkov.helpers.WithLogin;
import org.starkov.tests.web.domain.Currencies;
import org.starkov.tests.web.pages.MainPage;
import org.starkov.tests.web.pages.portfolioPages.DemoPortfolioMainPage;
import org.starkov.tests.web.pages.portfolioPages.GrowthPage;

import static io.qameta.allure.Allure.step;

@Tags({@Tag("Web"), @Tag("DemoPortfolio")})
@Epic("UI")
@Feature("Демо портфель")
@Owner("nikita.starkov")
public class DemoPortfolioTests extends WebTestBase {
    public static final DemoPortfolioMainPage demoPortMP = new DemoPortfolioMainPage();
    public static final MainPage mainPage = new MainPage();
    public static final GrowthPage growthPage = new GrowthPage();

    @Test
    @Story("Проверка пути")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка, что после выбора категории из списка проставляется путь")
    public void categorySelectFromListTest() {
        step("Кликаем, для перехода к демо-портфелю", mainPage::demoPortfolioBtnClick);
        step("Кликаем, для перехода к первой категории из списка", demoPortMP::clickToCategoryFromList);
        step("Проверяем, что категория проставилась в путь", demoPortMP::categoryPathCheck);
    }

    @Test
    @Story("Проверка пути")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка, что после выбора категории на диаграмме проставляется путь")
    public void categorySelectFromDiagramTest() {
        step("Кликаем, для перехода к демо-портфелю", mainPage::demoPortfolioBtnClick);
        step("Кликаем в левую часть диаграммы, для перехода к категории",
                demoPortMP::diagramCategoryClick);
        step("Проверяем, что категория проставилась в путь", demoPortMP::categoryPathIsNotNull);
    }

    @Test
    @Story("График стоимости портфеля")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка, что при наведении на график стоимости портфеля, появляется подсказка")
    public void graphicValueTest() {
        step("Кликаем, для перехода к демо-портфелю", mainPage::demoPortfolioBtnClick);
        step("Кликаем на вкладку 'Рост'", () -> demoPortMP.clickToTabByName("Рост"));
        step("Наводим мышкой, на график стоимости портфеля", growthPage::hoverOnValueGraph);
        step("Проверяем окно подсказки", growthPage::checkValueGraphTooltip);
    }

    @ParameterizedTest(name = "Проверка расчетов в валюте - {1}")
    @EnumSource(Currencies.class)
    @WithLogin
    @Story("Валютная конвертация")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка, что при смене валюты меняются данные на странице")
    public void currencyDisplayTest(Currencies currency) {
        step("Открываем окно выбора валют", demoPortMP::openCurrencySelector);
        step("Выбираем валюту", () -> demoPortMP.selectCurrency(currency));
        step("Проверяем, что в карточке со стоимостью портфеля поменялась валюта",
                () -> demoPortMP.statsCardCurrencyCheck("Стоимость", currency));
        step("Проверяем, что в карточке с прибылью портфеля поменялась валюта",
                () -> demoPortMP.statsCardCurrencyCheck("Прибыль", currency));
    }
}
