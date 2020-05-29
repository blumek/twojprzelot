package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequestBuilder.QueryKey.*;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequestBuilder.SCHEDULED_FLIGHT_RESOURCE_URI;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequestBuilder.Status.SCHEDULED;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequestBuilder.Type.DEPARTURE;

class ScheduledFlightRequestBuilderTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Class<ScheduledFlightAE[]> SCHEDULED_FLIGHT_ARRAY_TYPE = ScheduledFlightAE[].class;
    private static final String IATA_NUMBER_VALUE = "IATA_CODE_VALUE";
    private static final String ICAO_NUMBER_VALUE = "ICAO_CODE_VALUE";

    private ScheduledFlightRequestBuilder scheduledFlightRequestBuilder;
    private AviationEdgeRequest<ScheduledFlightAE> expectedRequest;

    @BeforeEach
    void setUp() {
        scheduledFlightRequestBuilder = new ScheduledFlightRequestBuilder(BASE_URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER);

        expectedRequest = new AviationEdgeRequest<>(SCHEDULED_FLIGHT_ARRAY_TYPE, BASE_URL + SCHEDULED_FLIGHT_RESOURCE_URI,
                API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
    }

    @Test
    void buildTest() {
        AviationEdgeRequest<ScheduledFlightAE> request = scheduledFlightRequestBuilder.build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataNumberTest() {
        expectedRequest.addQueryParameter(FLIGHT_IATA_NUMBER.getKey(), IATA_NUMBER_VALUE);

        AviationEdgeRequest<ScheduledFlightAE> request = scheduledFlightRequestBuilder
                .iataNumber(IATA_NUMBER_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void iataNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightRequestBuilder.iataNumber(null));
    }

    @Test
    void iataNumberTest_blankTextPassed() {
        assertThrows(IllegalArgumentException.class, () -> scheduledFlightRequestBuilder.iataNumber("  "));
    }

    @Test
    void icaoNumberTest() {
        expectedRequest.addQueryParameter(FLIGHT_ICAO_NUMBER.getKey(), ICAO_NUMBER_VALUE);

        AviationEdgeRequest<ScheduledFlightAE> request = scheduledFlightRequestBuilder
                .icaoNumber(ICAO_NUMBER_VALUE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void icaoNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightRequestBuilder.icaoNumber(null));
    }

    @Test
    void icaoNumberTest_blankTextPassed() {
        assertThrows(IllegalArgumentException.class, () -> scheduledFlightRequestBuilder.icaoNumber("  "));
    }

    @Test
    void typeTest() {
        expectedRequest.addQueryParameter(TYPE.getKey(), DEPARTURE.getValue());

        AviationEdgeRequest<ScheduledFlightAE> request = scheduledFlightRequestBuilder
                .type(DEPARTURE)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void typeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightRequestBuilder.type(null));
    }

    @Test
    void statusTest() {
        expectedRequest.addQueryParameter(STATUS.getKey(), SCHEDULED.getValue());

        AviationEdgeRequest<ScheduledFlightAE> request = scheduledFlightRequestBuilder
                .status(SCHEDULED)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void statusTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightRequestBuilder.status(null));
    }

    @Test
    void limitTest() {
        expectedRequest.addQueryParameter(LIMIT.getKey(), "1");

        AviationEdgeRequest<ScheduledFlightAE> request = scheduledFlightRequestBuilder
                .limit(1)
                .build();

        assertEquals(expectedRequest, request);
    }

    @Test
    void statusTest_zeroPassed() {
        assertThrows(IllegalArgumentException.class, () -> scheduledFlightRequestBuilder.limit(0));
    }

    @Test
    void statusTest_negativeValuePassed() {
        assertThrows(IllegalArgumentException.class, () -> scheduledFlightRequestBuilder.limit(-1));
    }
}