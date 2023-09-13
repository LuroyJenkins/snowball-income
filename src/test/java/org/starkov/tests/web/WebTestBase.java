package org.starkov.tests.web;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import config.ProjectConfigValidator;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.starkov.drivers.BrowserstackIosDriver;
import org.starkov.helpers.Attachments;
import org.starkov.helpers.DriverConfig;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class WebTestBase{
    private static final Faker faker = new Faker();
    public static String email = faker.internet().emailAddress(),
            password = faker.internet()
                    .password(8, 10, true, false, true);

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        selectDriver();
    }

    private static void selectDriver() {
        switch (ProjectConfigValidator.config.runIn()) {
            case "browser_selenoid":
            case "browser_local":
                DriverConfig.configure();
                break;
            case "ios_browserstack":
                Configuration.browser = BrowserstackIosDriver.class.getName();
                break;
        }
    }

    @BeforeEach
    @Step("Open {Project.config.baseUrl()}")
    public void beforeEach() {
        open(ProjectConfigValidator.config.baseUrl());
    }

    @AfterEach
    @Step("Save artifacts and close webdriver")
    public void afterEach() {
        attachEnvDependingTestArtifacts();
        closeWebDriver();
    }

    private void attachEnvDependingTestArtifacts() {
        Attachments.pageSource();
        switch (ProjectConfigValidator.config.runIn()) {
            case "ios_browserstack":
                Attachments.videoBrowserstack();
                break;
            case "browser_selenoid":
                Attachments.screenshotAs("Last screenshot");
                Attachments.videoSelenoid();
                break;
            case "browser_local":
                Attachments.screenshotAs("Last screenshot");
                if (!ProjectConfigValidator.config.browser().equals("firefox")) {
                    Attachments.browserConsoleLogs();
                }
                break;
        }
    }
}
