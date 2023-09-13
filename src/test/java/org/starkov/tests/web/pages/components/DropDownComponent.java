package org.starkov.tests.web.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DropDownComponent {

    private final SelenideElement dropDownLocator = $(".dropdown-menu");
    private final ElementsCollection navItems = dropDownLocator.$$(".navi-item");

    public void selectItem(String itemText) {
        navItems.findBy(text(itemText)).click();
    }
}
