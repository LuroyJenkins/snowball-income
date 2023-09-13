package org.starkov.tests.web.pages.portfolioPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GrowthPage {
    private SelenideElement portfolioValue = $("#apexchartshistoryxchart"),
            portfolioValueGraph = portfolioValue.$(".apexcharts-graphical"),
            valueGraphTooltip = portfolioValue.$(".apexcharts-tooltip");


    public GrowthPage hoverOnValueGraph() {
        sleep(2000);
        actions().moveToElement(portfolioValueGraph).perform();
        return this;
    }

    public void checkValueGraphTooltip() {
        valueGraphTooltip.shouldBe(visible)
                .shouldHave(text("Портфель"))
                .shouldHave(text("S&P 500"));
    }
}
