package org.starkov.tests.api.models.portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioModel {
    private String name;
    private int goalType;
    private double goalValue;
    private String goalCurrency;
    private int type;
    private String broker;
    private boolean applyTaxesOnPaidDividends;
    private boolean doNotAdjustXIRR;
    private boolean doNotAdjustCashDividendsGoesThroughWithdrawals;
    private int dividendTaxPercent;
    @JsonProperty("isComposite")
    private boolean isComposite;
    @JsonProperty("isAutoSyncEnabled")
    private boolean isAutoSyncEnabled;
    private boolean shareIsPublic;
    private String sharePublicKey;
    private List<PortfolioModel> childPortfolios;
    private boolean trackCash;
    private int trackCashType;
    private boolean removeCashAssets;
    private boolean useCategories;
    private String brokerCommission;
    private int viewType;
    private String syncProvider;
    private String syncParam;
    private int order;
    private boolean hidden;
    private boolean setupRequired;
    private boolean hasHoldings;
    private String defaultCurrency;
    private List<PortfolioNotificationSettings> portfolioNotificationSettings;
    @JsonProperty("isValid")
    private boolean isValid;
    private List<String> validationErrors;
    private String id;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class PortfolioNotificationSettings {
    private int notificationType;
    private int deliveryMethod;
}
