package org.starkov.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.starkov.tests.web.pages.MainPage;

import static io.qameta.allure.Allure.step;

@Tags({@Tag("Web"), @Tag("Video")})
@Epic("UI")
@Feature("Видеоплеер")
@Owner("nikita.starkov")
public class VideoContentTests extends WebTestBase {
    public static final MainPage mainPage = new MainPage();

    @ParameterizedTest(name = "Проверка, что при просмотре ролика № {0} , другие не воспроизводятся")
    @Story("Ленивое воспроизведение видео")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка, что ролики не воспроизводятся в фоне")
    @ValueSource(ints = {1, 2, 3})
    public void autoPlayedTest(int videoIndex) {
        step("Прокручиваем до нужного видео", () -> mainPage.scrollToVideo(videoIndex));
        step("Проверяем, что видео проигрывается", () -> mainPage.checkVideoIsPlaying(videoIndex));
        step("Проверяем, количество видео которые не проигрываются",
                () -> mainPage.checkLazyVideo(2));
    }

    @Test
    @Story("Pause/Play в плеере")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка остановки видео в плеере")
    public void stopPlayingTest() {
        step("Останавливаем видео", mainPage::switchVideoPlayerMode);
        step("Проверяем, что видео не проигрывается", () -> mainPage.checkVideoIsNotPlaying(0));
        step("Включаем видео", mainPage::switchVideoPlayerMode);
        step("Проверяем, что видео проигрывается", () -> mainPage.checkVideoIsPlaying(0));
    }

    @Test
    @Story("Полноэкранный режим")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка открытия видео в полноэкранном режиме")
    public void fullModeVideoTest() {
        step("Открываем видео в полноэкранном режиме", () -> mainPage.openFullscreenVideo(0));
        step("Проверяем, что видео открылось в полный экран", () -> mainPage.fullModeCheck(0));
    }
}
