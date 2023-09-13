package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Sources({"system:properties",
        "classpath:config/${runIn}.properties"})
public interface ProjectConfig extends Config {
    String remoteDriver();

    String baseUrl();

    //    @Key("browser")
    String browser();

    String browserSize();

    //    @Key("runIn")
    String runIn();

    String user();

    String key();

    String device();

    String platformVersion();

    String apiBaseUrl();

    String project();

    String build();

    String name();

}
