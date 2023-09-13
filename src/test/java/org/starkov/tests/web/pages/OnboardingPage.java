package org.starkov.tests.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class OnboardingPage {
    SelenideElement greetingsText = $(".text-center");

    public void greetingTextCheck() {
        greetingsText.shouldHave(text("Добро пожаловать в Snowball Income"));
    }
}
