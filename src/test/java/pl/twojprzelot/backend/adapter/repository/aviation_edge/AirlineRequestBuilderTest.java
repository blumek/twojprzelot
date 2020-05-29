package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.AirlineRequestBuilder.AIRLINE_RESOURCE_URI;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.AirlineRequestBuilder.QueryParameter.IATA_CODE;

class AirlineRequestBuilderTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Class<AirlineAE[]> AIRLINE_ARRAY_TYPE = AirlineAE[].class;
    private static final String IATA_CODE_VALUE = "IATA_CODE_VALUE";

    private AirlineRequestBuilder airlineRequestBuilder;
    private AviationEdgeRequest<AirlineAE> expectedRequest;

    @BeforeEach
    void setUp() {
        airlineRequestBuilder = new AirlineRequestBuilder(BASE_URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER);

        expectedRequest = new AviationEdgeRequest<>(AIRLINE_ARRAY_TYPE, BASE_URL + AIRLINE_RESOURCE_URI,
                API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
    }

    @Test
    void buildTest() {
        AviationEdgeRequest<AirlineAE> request = airlineRequestBuilder.build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataTest() {
        expectedRequest.addQueryParameter(IATA_CODE.getKey(), IATA_CODE_VALUE);

        AviationEdgeRequest<AirlineAE> request = airlineRequestBuilder
                .iataCode(IATA_CODE_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }
}