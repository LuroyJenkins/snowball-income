package org.starkov.tests.api.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddPublicRespModel {
    private Entity entity;
    @JsonProperty("isValid")
    private boolean isValid;
    private String validationErrors;
    private String id;
}

@Data
class Entity {
    private String userId;
    private String user;
    private boolean receivesUpdatesAndPromos;
    private boolean receivesAccountUpdates;
    private boolean receivesPortfolioUpdates;
    private String preferredLanguage;
    private boolean canChangeLanguage;
    private String preferredLocale;
    private String publicInvestorName;
    private String publicInvestorLinks;
    private String publicInvestorLogo;
    private String id;
}
