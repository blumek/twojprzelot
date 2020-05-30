package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.FlightRequestBuilder.QueryKey.*;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.FlightRequestBuilder.FLIGHT_RESOURCE_URI;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.FlightRequestBuilder.Status.STARTED;

class FlightRequestBuilderTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Class<FlightAE[]> FLIGHT_ARRAY_TYPE = FlightAE[].class;
    private static final String IATA_NUMBER_VALUE = "IATA_CODE_VALUE";
    private static final String ICAO_NUMBER_VALUE = "ICAO_CODE_VALUE";

    private FlightRequestBuilder flightRequestBuilder;
    private AviationEdgeRequest<FlightAE> expectedRequest;

    @BeforeEach
    void setUp() {
        flightRequestBuilder = new FlightRequestBuilder(BASE_URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER);

        expectedRequest = new AviationEdgeRequest<>(FLIGHT_ARRAY_TYPE, BASE_URL + FLIGHT_RESOURCE_URI,
                API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
    }

    @Test
    void buildTest() {
        AviationEdgeRequest<FlightAE> request = flightRequestBuilder.build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataNumberTest() {
        expectedRequest.addQueryParameter(FLIGHT_IATA_NUMBER.getKey(), IATA_NUMBER_VALUE);

        AviationEdgeRequest<FlightAE> request = flightRequestBuilder
                .iataNumber(IATA_NUMBER_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> flightRequestBuilder.iataNumber(null));
    }

    @Test
    void iataNumberTest_blankTextPassed() {
        assertThrows(IllegalArgumentException.class, () -> flightRequestBuilder.iataNumber("  "));
    }

    @Test
    void icaoNumberTest() {
        expectedRequest.addQueryParameter(FLIGHT_ICAO_NUMBER.getKey(), ICAO_NUMBER_VALUE);

        AviationEdgeRequest<FlightAE> request = flightRequestBuilder
                .icaoNumber(ICAO_NUMBER_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void icaoNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> flightRequestBuilder.icaoNumber(null));
    }

    @Test
    void icaoNumberTest_blankTextPassed() {
        assertThrows(IllegalArgumentException.class, () -> flightRequestBuilder.icaoNumber("  "));
    }

    @Test
    void statusTest() {
        expectedRequest.addQueryParameter(STATUS.getKey(), STARTED.getValue());

        AviationEdgeRequest<FlightAE> request = flightRequestBuilder
                .status(STARTED)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void statusTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> flightRequestBuilder.status(null));
    }

    @Test
    void limitTest() {
        expectedRequest.addQueryParameter(LIMIT.getKey(), "1");

        AviationEdgeRequest<FlightAE> request = flightRequestBuilder
                .limit(1)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void statusTest_zeroPassed() {
        assertThrows(IllegalArgumentException.class, () -> flightRequestBuilder.limit(0));
    }

    @Test
    void statusTest_negativeValuePassed() {
        assertThrows(IllegalArgumentException.class, () -> flightRequestBuilder.limit(-1));
    }
}