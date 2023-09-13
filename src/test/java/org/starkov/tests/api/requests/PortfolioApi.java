package org.starkov.tests.api.requests;

import config.ProjectConfigValidator;
import org.starkov.tests.api.models.portfolio.PortfolioModel;
import org.starkov.tests.api.specs.BaseSpec;

import static io.restassured.RestAssured.given;
import static org.starkov.tests.api.specs.BaseSpec.defaultRequestSpec;

public class PortfolioApi {
    private final BaseSpec baseSpec = new BaseSpec();
    private final static String portfolioEndPoint = "extapi/api/MyPortfolio";
    private static final String schemaPath = "schemas/getMyPortfolio-json-schema.json";

    public PortfolioModel[] getMyPortfolios() {
        return given(defaultRequestSpec)
                .when()
                .get(ProjectConfigValidator.config.apiBaseUrl() + portfolioEndPoint)
                .then()
                .spec(baseSpec.getResponseSpec(200, schemaPath))
                .log().all()
                .extract().as(PortfolioModel[].class);
    }
}
