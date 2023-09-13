package org.starkov.tests.api.models.watchlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddWatchlistRespModel {
    private String ticker;
    private String exchange;
    private String currency;
    private String watchlistId;
    private String watchlistName;
    private String assetInfoId;
    private String note;
    private String color;
    private int order;
    private Double priceHighlightMin;
    private Double priceHighlightMax;
    @JsonProperty("isValid")
    private boolean isValid;
    private List<String> validationErrors;
    private String id;
}
