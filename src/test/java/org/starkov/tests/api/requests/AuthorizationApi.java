package org.starkov.tests.api.requests;

import config.Project;
import org.starkov.tests.api.models.auth.AuthRequestModel;
import org.starkov.tests.api.models.auth.AuthResponseModel;
import org.starkov.tests.api.specs.BaseSpec;

import static config.App.config;
import static io.restassured.RestAssured.given;
import static org.starkov.tests.api.specs.AuthSpec.authRequestSpec;

public class AuthorizationApi {
    private static final BaseSpec spec = new BaseSpec();
    private final static String apiEndPoint = "api/account/login";
    private static final String authSchemaPath = "schemas/getAuth-json-schema.json";

    public static AuthResponseModel getAuthorization() {
        return given(authRequestSpec)
                .body(new AuthRequestModel(config.login(), config.password()))
                .when()
                .post(Project.config.apiBaseUrl() + apiEndPoint)
                .then()
                .spec(spec.getResponseSpec(200, authSchemaPath))
                .log().all()
                .extract().as(AuthResponseModel.class);
    }
}
