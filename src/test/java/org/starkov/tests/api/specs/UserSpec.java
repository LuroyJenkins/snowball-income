package org.starkov.tests.api.specs;

import io.restassured.specification.RequestSpecification;
import org.starkov.tests.api.requests.AuthorizationApi;

import java.util.Map;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.MULTIPART;
import static org.starkov.helpers.CustomAllureListener.withCustomTemplates;

public class UserSpec extends BaseSpec{

    public static RequestSpecification getReqMultipart(Map<String, String> formData){
        RequestSpecification formDataRequestSpec = with()
                .filter(withCustomTemplates())
                .contentType(MULTIPART)
                .header("Authorization", "Bearer " +
                        AuthorizationApi.getAuthorization().getLoginProps().getAccessToken());
        for (Map.Entry<String, String> entry : formData.entrySet()){
            formDataRequestSpec.multiPart(entry.getKey(), entry.getValue());
        }
        return formDataRequestSpec;
    }
}
