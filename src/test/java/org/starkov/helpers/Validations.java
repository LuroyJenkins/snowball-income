package org.starkov.helpers;

import static org.assertj.core.api.Assertions.assertThat;

public class Validations {

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue)
                .withFailMessage("'%s' value is null or empty. " +
                        "Please add this value to the configuration file and then try again.", propertyName)
                .isNotEmpty();
    }
}
