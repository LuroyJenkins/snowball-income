package org.starkov.tests.api.dataProviders;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Map;
import java.util.stream.Stream;

public class UserDataProvider {
    static Stream<Arguments> addPublicLinksTest() {
        return Stream.of(
                Arguments.of("Test1", Map.of("PublicInvestorLinks[0].Name", "VK",
                        "PublicInvestorLinks[0].Url", "https://vk.com/")),
                Arguments.of("Test22132121313", Map.of("PublicInvestorLinks[0].Url", "https://twitter.com/",
                        "PublicInvestorLinks[0].Name", "Twitter",
                        "PublicInvestorLinks[1].Name", "VK",
                        "PublicInvestorLinks[1].Url", "https://vk.com/")),
                Arguments.of("___Test___", Map.of(
                        "PublicInvestorLinks[0].Name", "VK",
                        "PublicInvestorLinks[0].Url", "https://vk.com/",
                        "PublicInvestorLinks[1].Name", "YouTube",
                        "PublicInvestorLinks[1].Url", "https://youtube.com/",
                        "PublicInvestorLinks[2].Name", "Twitter",
                        "PublicInvestorLinks[2].Url", "https://twitter.com/")
                )
        );
    }
}
