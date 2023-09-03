package org.starkov.tests.web.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Currencies {
    RUB("Рубль", "RUB", "₽"),
    USD("Доллар США", "USD", "$"),
    EUR("Евро", "EUR", "€");

    private final String description;
    private final String notation;
    private final String symbol;

    public String getDescription() {
        return description;
    }

    public String getNotation() {
        return notation;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return description;
    }
}
