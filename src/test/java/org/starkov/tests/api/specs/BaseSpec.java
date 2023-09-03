package org.starkov.tests.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.starkov.tests.api.requests.AuthorizationApi;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.starkov.helpers.CustomAllureListener.withCustomTemplates;

public class BaseSpec {
    public static RequestSpecification defaultRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .header("Authorization", "Bearer " +
                    new AuthorizationApi().getAuthorization().getLoginProps().getAccessToken());


    public ResponseSpecification getResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(statusCode)
                .build();
    }

    public ResponseSpecification getResponseSpec(int statusCode, String jsonSchemaPath) {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(statusCode)
                .expectBody(matchesJsonSchemaInClasspath(jsonSchemaPath))
                .build();
    }

}
