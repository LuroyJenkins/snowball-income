package org.starkov.helpers;

import config.ProjectConfig;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    public static String videoUrl(String sessionId) {
        String url = format("https://automate.browserstack.com/api/v1/sessions/%s.json", sessionId);

        return given()
                .auth().basic(config.user(), config.key())
                .when()
                .get(url)
                .then()
                .extract().path("sessionParams.outputCapabilities.\"browserstack.video\"");

    }
}
