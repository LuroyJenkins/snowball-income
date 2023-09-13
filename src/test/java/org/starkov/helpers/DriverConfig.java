package org.starkov.helpers;

import com.codeborne.selenide.Configuration;
import config.ProjectConfigValidator;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static config.ProjectConfigValidator.isRemoteDriver;
import static java.lang.String.format;

public class DriverConfig {
    public static void configure() {
        Configuration.browserSize = ProjectConfigValidator.config.browserSize();
        Configuration.baseUrl = ProjectConfigValidator.config.baseUrl();
        Configuration.browser = ProjectConfigValidator.config.browser();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (isRemoteDriver()) {
            Configuration.remote = ProjectConfigValidator.config.remoteDriver()
                    .replace("https://", format("https://%s:%s@", ProjectConfigValidator.config.user(), ProjectConfigValidator.config.key()));
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}
