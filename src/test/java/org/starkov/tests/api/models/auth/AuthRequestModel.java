package org.starkov.tests.api.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestModel {

    private String username;
    private String password;
}
