package org.starkov.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tags({@Tag("Web"), @Tag("Comments"), @Tag("Mobile")})
@Epic("UI")
@Feature("Отзывы")
@Owner("nikita.starkov")
public class CommentsTests extends WebTestBase{

    @Test
    @Story("Отзывы на главной странице")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка прокрутки карусели отзывов")
    public void scrollingCommentsTest() {
        step("Кликаем по переходу на другую страницу с комментариями",
                () -> mainPage.clickIndicatorComments(generatedIndex1to5));
        step("Проверяем, подсвечивается ли индикатор, на котором мы находимся",
                () -> mainPage.activeIndicatorCheck(generatedIndex1to5));
        step("Проверяем, что страница с индексом, таким же как у индикатора - активна",
                () -> mainPage.activeCommentsCheck(generatedIndex1to5));
    }
}
