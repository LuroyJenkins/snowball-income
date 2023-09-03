package org.starkov.drivers;

import com.codeborne.selenide.Configuration;
import config.Project;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import static config.Project.isRemoteDriver;
import static java.lang.String.format;

public class LocalAndRemoteWebDriver {
    public static void configure() {
        Configuration.browserSize = Project.config.browserSize();
        Configuration.baseUrl = Project.config.baseUrl();
        Configuration.browser = Project.config.browser();
        MutableCapabilities capabilities = new DesiredCapabilities();
        if (isRemoteDriver()) {
            Configuration.remote = Project.config.remoteDriver()
                    .replace("https://", format("https://%s:%s@", Project.config.user(), Project.config.key()));
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
        }
    }
}
