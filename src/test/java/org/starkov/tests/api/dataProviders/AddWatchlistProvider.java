package org.starkov.tests.api.dataProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.starkov.tests.api.domain.Asset;

import java.util.stream.Stream;

import static org.starkov.tests.web.domain.Currencies.*;

public class AddWatchlistProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        new Asset("AMD", "US", USD.getNotation()),
                        new Asset("AAPL", "US", USD.getNotation()),
                        new Asset("INTC", "US", USD.getNotation())
                ),
                Arguments.of(
                        new Asset("VOW", "F", EUR.getNotation()),
                        new Asset("YNDX", "MCX", RUB.getNotation()),
                        new Asset("TSLA", "US", USD.getNotation())
                )
        );
    }
}
