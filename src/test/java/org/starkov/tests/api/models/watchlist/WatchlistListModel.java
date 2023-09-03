package org.starkov.tests.api.models.watchlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WatchlistListModel {
    private String name;
    private String note;
    private String currency;
    private String createdDate;
    private int assetsCount;
    private int order;
    @JsonProperty("isValid")
    private boolean isValid;
    private Object validationErrors;
    private String id;
}
