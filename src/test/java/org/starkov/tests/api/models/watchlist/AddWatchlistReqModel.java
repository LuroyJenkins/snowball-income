package org.starkov.tests.api.models.watchlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddWatchlistReqModel {
        private String ticker;
        private String exchange;
        private String currency;
        private String watchlistId;
}
