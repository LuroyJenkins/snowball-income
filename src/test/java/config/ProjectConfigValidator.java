package config;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.starkov.helpers.Validations.validateProperty;

public class ProjectConfigValidator {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
    private static final Logger logger = LoggerFactory.getLogger(ProjectConfigValidator.class);

    static {
        if ("API".equals(System.getProperty("tag"))) {
            validateProperty(config.apiBaseUrl(), "apiBaseUrl");
        } else {
            validateEnvDependentProperties();
        }
        logger.info(config.toString());
    }

    private static void validateEnvDependentProperties() {
        switch (config.runIn()) {
            case "browser_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "browser_local":
                validateProperty(config.browser(), "browser");
                break;
            case "ios_browserstack":
                validateProperty(config.user(), "user");
                validateProperty(config.key(), "key");
                break;
            default:
                throw new IllegalStateException("Unexpected 'runIn' value: " + config);
        }
    }

    public static boolean isRemoteDriver() {
        return !(config.remoteDriver() == null) && !config.remoteDriver().isEmpty();
    }
}
