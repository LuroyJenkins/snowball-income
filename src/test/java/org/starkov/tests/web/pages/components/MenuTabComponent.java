package org.starkov.tests.web.pages.components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class MenuTabComponent {
    public final ElementsCollection menuTabs = $$(".menu-nav li");

    public void waitAllTabs(int count) {
        menuTabs.filterBy(visible).shouldHave(CollectionCondition.size(count));
    }
}
