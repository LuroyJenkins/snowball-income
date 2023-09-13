package org.starkov.tests.api.requests;

import config.ProjectConfigValidator;
import org.starkov.tests.api.models.auth.AuthRequestModel;
import org.starkov.tests.api.models.auth.AuthResponseModel;
import org.starkov.tests.api.specs.BaseSpec;

import static config.AppConfigValidator.config;
import static io.restassured.RestAssured.given;
import static org.starkov.tests.api.specs.AuthSpec.authRequestSpec;

public class AuthorizationApi {
    private BaseSpec spec = new BaseSpec();
    private final static String loginEndPoint = "api/account/login";
    private static final String authSchemaPath = "schemas/getAuth-json-schema.json";

    public AuthResponseModel getAuthorization() {
        return given(authRequestSpec)
                .body(new AuthRequestModel(config.login(), config.password()))
                .when()
                .post(ProjectConfigValidator.config.apiBaseUrl() + loginEndPoint)
                .then()
                .spec(spec.getResponseSpec(200, authSchemaPath))
                .log().all()
                .extract().as(AuthResponseModel.class);
    }
}
