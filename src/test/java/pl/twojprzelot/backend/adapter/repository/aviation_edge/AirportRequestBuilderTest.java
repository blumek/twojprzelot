package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.AirportRequestBuilder.AIRPORT_RESOURCE_URI;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.AirportRequestBuilder.QueryParameter.IATA_CODE;

class AirportRequestBuilderTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Class<AirportAE[]> AIRPORT_ARRAY_TYPE = AirportAE[].class;
    private static final String IATA_CODE_VALUE = "IATA_CODE_VALUE";

    private AirportRequestBuilder airportRequestBuilder;
    private AviationEdgeRequest<AirportAE> expectedRequest;

    @BeforeEach
    void setUp() {
        airportRequestBuilder = new AirportRequestBuilder(BASE_URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER);

        expectedRequest = new AviationEdgeRequest<>(AIRPORT_ARRAY_TYPE, BASE_URL + AIRPORT_RESOURCE_URI,
                API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
    }

    @Test
    void buildTest() {
        AviationEdgeRequest<AirportAE> request = airportRequestBuilder.build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataTest() {
        expectedRequest.addQueryParameter(IATA_CODE.getKey(), IATA_CODE_VALUE);

        AviationEdgeRequest<AirportAE> request = airportRequestBuilder
                .iataCode(IATA_CODE_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airportRequestBuilder.iataCode(null));
    }

    @Test
    void iataTest_blankTextPassed() {
        assertThrows(IllegalArgumentException.class, () -> airportRequestBuilder.iataCode("  "));
    }
}