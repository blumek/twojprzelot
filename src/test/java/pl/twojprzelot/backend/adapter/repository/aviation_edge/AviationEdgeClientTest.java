package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.*;

class AviationEdgeClientTest {
    private static final String API_KEY = "API_KEY";

    @Test
    void constructorTest_nullKey() {
        assertThrows(IllegalArgumentException.class, () -> new AviationEdgeClient(null));
    }

    @Test
    void constructorTest_blankKey() {
        assertThrows(IllegalArgumentException.class, () -> new AviationEdgeClient("  "));
    }

    @Test
    void createCountryRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY);
        CountryRequest countryRequest = aviationEdgeClient.createCountryRequest()
                .create();

        CountryRequest expectedCountryRequest = new CountryRequest.
                Builder("https://aviation-edge.com/v2/public", API_KEY, new RestTemplate())
                .create();

        assertEquals(expectedCountryRequest, countryRequest);
    }
}