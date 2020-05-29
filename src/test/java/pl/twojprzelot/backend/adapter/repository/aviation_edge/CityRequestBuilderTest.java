package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.CityRequestBuilder.CITY_RESOURCE_URI;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.CityRequestBuilder.QueryParameter.IATA_CODE;

class CityRequestBuilderTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Class<CityAE[]> CITY_ARRAY_TYPE = CityAE[].class;
    private static final String IATA_CODE_VALUE = "IATA_CODE_VALUE";

    private CityRequestBuilder cityRequestBuilder;
    private AviationEdgeRequest<CityAE> expectedRequest;

    @BeforeEach
    void setUp() {
        cityRequestBuilder = new CityRequestBuilder(BASE_URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER);

        expectedRequest = new AviationEdgeRequest<>(CITY_ARRAY_TYPE, BASE_URL + CITY_RESOURCE_URI,
                API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
    }

    @Test
    void buildTest() {
        AviationEdgeRequest<CityAE> request = cityRequestBuilder.build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataCodeTest() {
        expectedRequest.addQueryParameter(IATA_CODE.getKey(), IATA_CODE_VALUE);

        AviationEdgeRequest<CityAE> request = cityRequestBuilder
                .iataCode(IATA_CODE_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> cityRequestBuilder.iataCode(null));
    }

    @Test
    void iataCodeTest_blankTextPassed() {
        assertThrows(IllegalArgumentException.class, () -> cityRequestBuilder.iataCode("  "));
    }
}