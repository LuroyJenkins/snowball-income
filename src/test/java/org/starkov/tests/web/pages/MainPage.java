package org.starkov.tests.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage {
    private final ElementsCollection videosList = $$("video"),
            lazyVideosList = $$("video.lazy"),
            indicatorsList = $$(".carousel-indicators li"),
            commentsList = $$(".carousel-item");
    private final SelenideElement emailInput = $("[type=email]"),
            passwordInput = $("[type=password]"),
            submitBtn = $("[type=submit]"),
            loginBtn = $(".topbar").$(byText("Войти")),
            demoPortfolioBtn = $(".value-proposition").$(withText("демо портфель"));

    private static final Faker faker = new Faker();
    public static int generatedIndex1to5 = faker.number().numberBetween(1, 5);

    public MainPage scrollToVideo(int index) {
        videosList.get(index).scrollTo();
        return this;
    }

    public void checkLazyVideo(int countLazyVideos) {
        assertEquals(countLazyVideos, lazyVideosList.size());
    }

    public void checkVideoIsPlaying(int index) {
        videosList.get(index).shouldNotHave(attribute("paused"));
    }

    public void checkVideoIsNotPlaying(int index) {
        videosList.get(index).shouldHave(attribute("paused"));
    }

    public MainPage switchVideoPlayerMode() {
        videosList.first().click();
        return this;
    }

    public MainPage openFullscreenVideo(int index) {
        videosList.get(index).doubleClick();
        return this;
    }

    public void fullModeCheck(int index) {
        videosList.get(index).
                shouldHave(attribute("webkitDisplayingFullscreen", "true"));
    }

    public MainPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public MainPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public void submitBtnClick() {
        submitBtn.click();
    }

    public MainPage clickIndicatorComments(int index) {
        indicatorsList.get(index).click();
        return this;
    }

    public void activeIndicatorCheck(int index) {
        indicatorsList.get(index).shouldHave(cssClass("active"));
    }

    public void activeCommentsCheck(int index) {
        commentsList.get(index).shouldHave(cssClass("active"));
    }

    public void loginBtnClick() {
        loginBtn.click();
    }

    public void demoPortfolioBtnClick() {
        demoPortfolioBtn.click();
    }
}
