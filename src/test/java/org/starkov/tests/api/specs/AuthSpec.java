package org.starkov.tests.api.specs;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.starkov.helpers.CustomAllureListener.withCustomTemplates;

public class AuthSpec {

    public static RequestSpecification authRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON);
}
