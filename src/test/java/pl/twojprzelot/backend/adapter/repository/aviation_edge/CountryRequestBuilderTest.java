package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.CountryRequestBuilder.COUNTRY_RESOURCE_URI;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.CountryRequestBuilder.QueryParameter.ISO_2_CODE;

class CountryRequestBuilderTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Class<CountryAE[]> COUNTRY_ARRAY_TYPE = CountryAE[].class;
    private static final String ISO_2_CODE_VALUE = "ISO_2_CODE_VALUE";

    private CountryRequestBuilder countryRequestBuilder;
    private AviationEdgeRequest<CountryAE> expectedRequest;

    @BeforeEach
    void setUp() {
        countryRequestBuilder = new CountryRequestBuilder(BASE_URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER);

        expectedRequest = new AviationEdgeRequest<>(COUNTRY_ARRAY_TYPE, BASE_URL + COUNTRY_RESOURCE_URI,
                API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
    }

    @Test
    void buildTest() {
        AviationEdgeRequest<CountryAE> request = countryRequestBuilder.build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iso2CodeTest() {
        expectedRequest.addQueryParameter(ISO_2_CODE.getKey(), ISO_2_CODE_VALUE);

        AviationEdgeRequest<CountryAE> request = countryRequestBuilder
                .iso2Code(ISO_2_CODE_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iso2CodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> countryRequestBuilder.iso2Code(null));
    }

    @Test
    void iso2CodeTest_blankTextPassed() {
        assertThrows(IllegalArgumentException.class, () -> countryRequestBuilder.iso2Code("  "));
    }
}