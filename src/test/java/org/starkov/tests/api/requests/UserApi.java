package org.starkov.tests.api.requests;

import config.ProjectConfigValidator;
import org.starkov.tests.api.models.user.AddPublicRespModel;
import org.starkov.tests.api.models.user.GetPublicRespModel;
import org.starkov.tests.api.specs.BaseSpec;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.starkov.tests.api.specs.BaseSpec.defaultRequestSpec;
import static org.starkov.tests.api.specs.UserSpec.getReqMultipart;

public class UserApi {
    private final BaseSpec baseSpec = new BaseSpec();
    private final static String baseUserEndPoint = "api/user";
    private final static String updUserEndPoint = baseUserEndPoint + "/updatepublic";
    private final static String getUserEndPoint = baseUserEndPoint + "/getpublic";
    private static final String getSchemaPath = "schemas/getUserPublic-json-schema.json";
    private static final String addSchemaPath = "schemas/putUserPublic-json-schema.json";

    public GetPublicRespModel getPublicInfo() {
        return given(defaultRequestSpec)
                .when()
                .get(ProjectConfigValidator.config.apiBaseUrl() + getUserEndPoint)
                .then()
                .spec(baseSpec.getResponseSpec(200, getSchemaPath))
                .log().all()
                .extract().as(GetPublicRespModel.class);
    }

    public AddPublicRespModel addPublicInfo(String publicName, Map<String, String> publicLinks) {
        return given(getReqMultipart(publicLinks))
                .multiPart("PublicInvestorName", publicName)
                .when()
                .put(ProjectConfigValidator.config.apiBaseUrl() + updUserEndPoint)
                .then()
                .spec(baseSpec.getResponseSpec(200, addSchemaPath))
                .log().all()
                .extract().as(AddPublicRespModel.class);
    }
}
