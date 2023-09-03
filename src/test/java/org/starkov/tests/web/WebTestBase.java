package org.starkov.tests.web;

import com.github.javafaker.Faker;
import config.Project;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.starkov.tests.TestBase;
import org.starkov.tests.web.pages.AuthPage;
import org.starkov.tests.web.pages.MainPage;
import org.starkov.tests.web.pages.OnboardingPage;
import org.starkov.tests.web.pages.portfolioPages.DemoPortfolioMainPage;
import org.starkov.tests.web.pages.portfolioPages.GrowthPage;

import static com.codeborne.selenide.Selenide.open;

public class WebTestBase extends TestBase{
    public static final AuthPage authPage = new AuthPage();
    public static final MainPage mainPage = new MainPage();
    public static final OnboardingPage onboardingPage = new OnboardingPage();
    public static final DemoPortfolioMainPage demoPortMP = new DemoPortfolioMainPage();
    public static final GrowthPage growthPage = new GrowthPage();
    private static final Faker faker = new Faker();
    public static int generatedIndex1to5 = faker.number().numberBetween(1, 5);
    public static String email = faker.internet().emailAddress(),
            password = faker.internet()
                    .password(8, 10, true, false, true);

    @BeforeEach
    @Override
    @Step("Open {Project.config.baseUrl()}")
    public void beforeEach() {
//        open("/");
        open(Project.config.baseUrl());
    }
}
