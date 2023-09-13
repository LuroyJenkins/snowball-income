package org.starkov.tests.api.requests;

import config.ProjectConfigValidator;
import org.starkov.tests.api.models.watchlist.AddWatchlistReqModel;
import org.starkov.tests.api.models.watchlist.AddWatchlistRespModel;
import org.starkov.tests.api.models.watchlist.WatchlistListModel;
import org.starkov.tests.api.models.watchlist.WatchlistValueModel;
import org.starkov.tests.api.specs.BaseSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.starkov.tests.api.specs.BaseSpec.defaultRequestSpec;

public class WatchlistApi {
    private final BaseSpec baseSpec = new BaseSpec();
    private final static String watchlistApiEndPoint = "extapi/api/watchlistAsset";
    private final static String getListApiEndPoint = "extapi/api/watchlist/list";
    private final static String getValuesApiEndPoint = "extapi/api/watchlistAnalytics/assets";
    private static final String getWatchlistsSchemaPath = "schemas/getWatchlists-json-schema.json";
    private static final String addValueSchemaPath = "schemas/addWatchlistValue-json-schema.json";

    public WatchlistListModel[] getWatchlistList() {
        return given(defaultRequestSpec)
                .when()
                .get(ProjectConfigValidator.config.apiBaseUrl() + getListApiEndPoint)
                .then()
                .spec(baseSpec.getResponseSpec(200, getWatchlistsSchemaPath))
                .log().all()
                .extract().as(WatchlistListModel[].class);
    }

    public AddWatchlistRespModel addValueForWatch(String ticker, String exchange, String currency, String watchlistId) {
        return given(defaultRequestSpec)
                .body(new AddWatchlistReqModel(ticker, exchange, currency, watchlistId))
                .post(ProjectConfigValidator.config.apiBaseUrl() + watchlistApiEndPoint)
                .then()
                .spec(baseSpec.getResponseSpec(200, addValueSchemaPath))
                .log().all()
                .body("watchlistId", is(watchlistId))
                .extract().as(AddWatchlistRespModel.class);

    }

    public WatchlistValueModel[] getWatchlistValues(String watchlistId, String changePeriod) {
        return given(defaultRequestSpec)
                .when()
                .queryParam("watchlistId", watchlistId)
                .queryParam("changePeriod", changePeriod)
                .get(ProjectConfigValidator.config.apiBaseUrl() + getValuesApiEndPoint)
                .then()
                .spec(baseSpec.getResponseSpec(200))
                .log().all()
                .body("[0].watchlistId", is(watchlistId))
                .extract().as(WatchlistValueModel[].class);
    }

    public void delValueFromWatchlist(String id) {
        given(defaultRequestSpec)
                .when()
                .delete(ProjectConfigValidator.config.apiBaseUrl() + watchlistApiEndPoint + "/" + id)
                .then()
                .spec(baseSpec.getResponseSpec(200))
                .log().all()
                .extract()
                .response();
    }
}
