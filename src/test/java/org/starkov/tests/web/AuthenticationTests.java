package org.starkov.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static io.qameta.allure.Allure.step;

@Tags({@Tag("Web"), @Tag("Auth"), @Tag("Mobile")})
@Epic("UI")
@Feature("Авторизация")
@Owner("nikita.starkov")
public class AuthenticationTests extends WebTestBase{

    @Test()
    @Story("Быстрый старт")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка быстрого старта использования сервиса")
    public void fastStartTest() {
        step("Ввод данных для регистрации",
                () -> mainPage.setEmail(email)
                        .setPassword(password)
                        .submitBtnClick());
        step("Проверка текста приветствия", onboardingPage::greetingTextCheck);
    }

    @ParameterizedTest(name = "Проверка успешной авторизации c Email = {1}")
    @Story("Стандартная авторизация")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка успешной авторизации")
    @CsvFileSource(resources = "/data/AuthData.txt")
    public void authSuccessTest(String email, String password) {
        step("Кликаем на кнопку перехода к форме авторизации", mainPage::loginBtnClick);
        step("Ввод данных для регистрации", () -> authPage.setEmail(email)
                .setPassword(password)
                .submitBtnClick());
        step("Проверка текста приветствия", onboardingPage::greetingTextCheck);
    }

    @Test
    @Story("Вход без пароля")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка вспомогательного сообщения при попытке войти без пароля")
    public void authWithoutPass() {
        step("Кликаем на кнопку перехода к форме авторизации", mainPage::loginBtnClick);
        step("Ввод данных для регистрации", () -> authPage.setEmail(email)
                .submitBtnClick());
        step("Проверка вспомогательного текста", () -> authPage.checkHelpText("Введите пароль"));
    }

    @ParameterizedTest(name = "Проверка вспомогательного сообщения - {1}")
    @Story("Невалидный ввод пароля")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка вспомогательного сообщения при некорректном вводе пароля")
    @CsvSource(value = {"1, Минимум 3 символа",
            "mMazsvSdKnERwpoN6Dsjnrnm5PYEfvX0MbPbJ35Fi9oiqL2Xqq1, Максимум 50 символов"})
    public void authHelpTextTest(String password, String helpText) {
        step("Кликаем на кнопку перехода к форме авторизации", mainPage::loginBtnClick);
        step("Ввод данных для регистрации",
                () -> authPage.setPassword(password).setEmail(email));
        step("Проверка вспомогательного текста", () -> authPage.checkHelpText(helpText));
    }
}
