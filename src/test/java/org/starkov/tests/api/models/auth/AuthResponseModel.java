package org.starkov.tests.api.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponseModel {

    private LoginProps loginProps;

    @Data
    public class LoginProps {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("expires_in")
        private int expiresIn;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("refresh_token")
        private String refreshToken;

        private Object portfolio;

        private boolean hasPortfolio;

        private String analyticsUserId;
    }
}
