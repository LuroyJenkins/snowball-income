package config;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.starkov.helpers.Validations.validateProperty;

public class AppConfigValidator {

    public static AppConfig config = ConfigFactory.create(AppConfig.class);
    private static final Logger logger = LoggerFactory.getLogger(ProjectConfigValidator.class);

    static {
        validateProperty(config.login(), "login");
        validateProperty(config.password(), "password");
        logger.info(config.toString());
    }
}
