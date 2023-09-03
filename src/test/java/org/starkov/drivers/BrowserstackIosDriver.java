package org.starkov.drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static config.Project.config;
import static io.appium.java_client.remote.MobileBrowserType.SAFARI;

public class BrowserstackIosDriver implements WebDriverProvider {

    private static final String remoteDriver = "http://hub.browserstack.com/wd/hub";

    public static URL getBrowserstackUrl() {
        try {
            return new URL(remoteDriver);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserstack.user", config.user());
        desiredCapabilities.setCapability("browserstack.key", config.key());
        desiredCapabilities.setCapability("browser", SAFARI);
        desiredCapabilities.setCapability("device", config.deviceName());
        desiredCapabilities.setCapability("os_version", config.platformVersion());
        desiredCapabilities.setCapability("project", config.project());
        desiredCapabilities.setCapability("build", config.build());
        desiredCapabilities.setCapability("name", config.name());
        return new RemoteWebDriver(getBrowserstackUrl(), desiredCapabilities);
    }
}
