package org.starkov.tests.web.pages.components;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class NavigationTab {
    private final ElementsCollection navTabs = $$(".nav-tabs li");

    public void clickToTab(String tabText){
        navTabs.findBy(text(tabText)).click();
    }
}
