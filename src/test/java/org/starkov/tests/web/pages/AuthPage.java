package org.starkov.tests.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {
    private final SelenideElement emailInput = $("[type=email]"),
            passwordInput = $("[type=password]"),
            submitBtn = $("[type=submit]"),
            helpBlock = $(".fv-help-block");

    public AuthPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public AuthPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public void submitBtnClick() {
        submitBtn.click();
    }

    public void checkHelpText(String helpText) {
        helpBlock.shouldHave(text(helpText));
    }
}
