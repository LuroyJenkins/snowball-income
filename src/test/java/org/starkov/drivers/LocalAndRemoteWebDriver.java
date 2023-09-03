package org.starkov.drivers;

import com.codeborne.selenide.Configuration;
import config.Project;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static config.Project.isRemoteDriver;
import static java.lang.String.format;

public class LocalAndRemoteWebDriver {
    public static void configure() {
        Configuration.browserSize = Project.config.browserSize();
        Configuration.baseUrl = Project.config.baseUrl();
        Configuration.browser = Project.config.browser();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (isRemoteDriver()) {
            Configuration.remote = Project.config.remoteDriver()
                    .replace("https://", format("https://%s:%s@", Project.config.user(), Project.config.key()));
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
        }
    }
}
