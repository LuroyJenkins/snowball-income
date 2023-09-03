package org.starkov.tests;

import com.codeborne.selenide.Configuration;
import config.Project;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.starkov.drivers.BrowserstackIosDriver;
import org.starkov.drivers.LocalAndRemoteWebDriver;
import org.starkov.helpers.Attachments;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        selectDriver();
    }

    private static void selectDriver() {
        switch (Project.config.runIn()) {
            case "browser_selenoid", "browser_local" -> LocalAndRemoteWebDriver.configure();
            case "ios_browserstack" -> Configuration.browser = BrowserstackIosDriver.class.getName();
        }
    }

    @BeforeEach
    @Step("Open the application(or browser)")
    public void beforeEach() {
        open();
    }

    @AfterEach
    @Step("Save artifacts and close webdriver")
    public void afterEach() {
        attachEnvDependingTestArtifacts();
        closeWebDriver();
    }

    private void attachEnvDependingTestArtifacts() {
        Attachments.pageSource();
        String sessionId = Attachments.getSessionId();
        switch (Project.config.runIn()) {
            case "ios_browserstack" -> {
                Attachments.videoBrowserstack(sessionId);
            }
            case "browser_selenoid" -> {
                Attachments.screenshotAs("Last screenshot");
                Attachments.videoSelenoid(sessionId);
            }
            case "browser_local" -> {
                Attachments.screenshotAs("Last screenshot");
                if (!Project.config.browser().equals("firefox")) {
                    Attachments.browserConsoleLogs();
                }
            }
        }
    }
}
