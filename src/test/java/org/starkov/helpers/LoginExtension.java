package org.starkov.helpers;

import com.codeborne.selenide.Selenide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.starkov.tests.api.models.auth.AuthResponseModel;
import org.starkov.tests.api.requests.AuthorizationApi;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;

public class LoginExtension implements BeforeEachCallback {

    private static final String STORAGE_AUTH_KEY = "persist:snowball-auth";
    private static final String AUTH_STORAGE_FILE_PATH = "data/AuthStorage.json";

    @Step("Подкладывание авторизационного токена")
    @Override
    public void beforeEach(ExtensionContext context) throws IOException {
        AuthResponseModel auth = new AuthorizationApi().getAuthorization();
        File fileAuthStorage = new File(Objects.requireNonNull(getClass()
                .getClassLoader().getResource(AUTH_STORAGE_FILE_PATH)).getFile());
        FileReader reader = new FileReader(fileAuthStorage);
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        JsonObject json = gson.fromJson(reader, JsonObject.class);

        json.addProperty("accessToken", "\\\"" + auth.getLoginProps().getAccessToken() + "\\\"");
        json.addProperty("refreshToken", "\\\"" + auth.getLoginProps().getRefreshToken() + "\\\"");

        open("/favicon.ico");

        Selenide.executeJavaScript(String.format("localStorage.setItem('" + STORAGE_AUTH_KEY + "', '%s')",
                new Gson().toJson(json)));
    }
}
