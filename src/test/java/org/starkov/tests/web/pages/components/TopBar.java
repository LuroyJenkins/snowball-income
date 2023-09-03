package org.starkov.tests.web.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TopBar {
    private final SelenideElement topBarLocator = $(".topbar");
    private final ElementsCollection topBarDropdowns = topBarLocator.$$(".topbar-dropdown");

    public void openCurrencySelector() {
        topBarDropdowns.last().click();
    }
}
