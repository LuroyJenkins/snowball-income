package org.starkov.tests.web.pages.portfolioPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.starkov.tests.web.domain.Currencies;
import org.starkov.tests.web.pages.components.DropDownMenu;
import org.starkov.tests.web.pages.components.MenuTab;
import org.starkov.tests.web.pages.components.NavigationTab;
import org.starkov.tests.web.pages.components.TopBar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DemoPortfolioMainPage {
    MenuTab menuTap = new MenuTab();
    NavigationTab navigationTab = new NavigationTab();
    TopBar topBar = new TopBar();
    DropDownMenu dropDownMenu = new DropDownMenu();
    private String categoryDescription;
    private static final ElementsCollection categoryBodies = $$(".categories-block .card-body"),
            canvasList = $$("canvas"),
            statsCards = $$(".stats-card");
    private static final SelenideElement categoryViewCard = categoryBodies.first(),
            categoryListCard = categoryBodies.get(1),
            categoryList = categoryListCard.$(".table-responsive"),
            categoryPath = categoryViewCard.$(".col"),
            portfolioCategoryCanvas = canvasList.first();


    public DemoPortfolioMainPage clickToCategoryFromList() {
        SelenideElement category = categoryList.$$("tr.grid-row").first();
        categoryDescription = category.$(".asset-info-description").getText();
        category.click();
        return this;
    }

    public void clickToTabByName(String tabText) {
        navigationTab.clickToTab(tabText);
    }

    public void categoryPathCheck() {
        categoryPath.shouldHave(text(categoryDescription));
    }

    public DemoPortfolioMainPage diagramCategoryClick() {
        sleep(2000);
        actions().moveToElement(portfolioCategoryCanvas, -100, 0).click().perform();
        return this;
    }

    public void categoryPathIsNotNull() {
        sleep(2000);
        int sizeOfPath = categoryPath.$$("li").size();
        assertNotEquals(1, sizeOfPath);
    }

    public DemoPortfolioMainPage openCurrencySelector() {
        menuTap.waitAllTabs(5);
        topBar.openCurrencySelector();
        return this;
    }

    public DemoPortfolioMainPage selectCurrency(Currencies currency) {
        dropDownMenu.selectItem(currency.getDescription());
        return this;
    }

    public void statsCardCurrencyCheck(String statsCardHeader, Currencies currency) {
        statsCards.filterBy(text(statsCardHeader)).first().shouldHave(text(currency.getSymbol()));
    }

}
