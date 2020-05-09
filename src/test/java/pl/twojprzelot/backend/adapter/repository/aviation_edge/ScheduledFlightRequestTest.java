package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequest.Status.SCHEDULED;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequest.Type.DEPARTURE;

@ExtendWith(MockitoExtension.class)
class ScheduledFlightRequestTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";
    private static final long LIMIT = 10;
    private static final String BASIC_REQUEST_URL = "BASE_URL/timetable?key=API_KEY";
    private static final String REQUEST_URL_WITH_IATA_NUMBER = "BASE_URL/timetable?key=API_KEY&flight_iata=IATA_NUMBER";
    private static final String REQUEST_URL_WITH_ICAO_NUMBER = "BASE_URL/timetable?key=API_KEY&flight_icao=ICAO_NUMBER";
    private static final String REQUEST_URL_WITH_LIMIT = "BASE_URL/timetable?key=API_KEY&limit=10";
    private static final String REQUEST_URL_WITH_TYPE = "BASE_URL/timetable?key=API_KEY&type=departure";
    private static final String REQUEST_URL_WITH_STATUS = "BASE_URL/timetable?key=API_KEY&status=scheduled";
    protected static final String FIRST_STATUS = "FIRST_STATUS";
    protected static final String SECOND_STATUS = "SECOND_STATUS";

    @Mock
    private RestTemplate restTemplate;

    private ScheduledFlightAE firstScheduledFlightAE;
    private ScheduledFlightAE secondScheduledFlightAE;

    @BeforeEach
    void setUp() {
        FlightIdentifierAE flightIdentifierAE = new FlightIdentifierAE();
        flightIdentifierAE.setIataNumber(IATA_NUMBER);
        flightIdentifierAE.setIcaoNumber(ICAO_NUMBER);

        firstScheduledFlightAE = new ScheduledFlightAE();
        firstScheduledFlightAE.setFlightIdentifier(flightIdentifierAE);
        firstScheduledFlightAE.setStatus(FIRST_STATUS);

        secondScheduledFlightAE = new ScheduledFlightAE();
        secondScheduledFlightAE.setFlightIdentifier(flightIdentifierAE);
        secondScheduledFlightAE.setStatus(SECOND_STATUS);
    }

    @Test
    void getTest_noResults_emptyListReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class))
                .thenReturn(new ScheduledFlightAE[]{});

        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<ScheduledFlightAE> scheduledFlights = request.get();

        assertTrue(scheduledFlights.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_noResults_nullReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class))
                .thenReturn(null);

        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<ScheduledFlightAE> scheduledFlights = request.get();

        assertTrue(scheduledFlights.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_twoResults() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class))
                .thenReturn(new ScheduledFlightAE[]{firstScheduledFlightAE, secondScheduledFlightAE});

        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        assertThat(request.get(), containsInAnyOrder(firstScheduledFlightAE, secondScheduledFlightAE));

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_internalServerError() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class))
                .thenThrow(HttpServerErrorException.InternalServerError.class);

        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<ScheduledFlightAE> scheduledFlights = request.get();
        assertTrue(scheduledFlights.isEmpty());

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_queryParams_iataNumber() {
        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .iataNumber(IATA_NUMBER)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("flight_iata", IATA_NUMBER));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_IATA_NUMBER, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_queryParams_iataNumber_null() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).iataNumber(null));
    }

    @Test
    void getTest_queryParams_iataNumber_blank() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).iataNumber(" "));
    }

    @Test
    void getTest_queryParams_icaoNumber() {
        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .icaoNumber(ICAO_NUMBER)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("flight_icao", ICAO_NUMBER));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_ICAO_NUMBER, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_queryParams_icaoNumber_null() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).icaoNumber(null));
    }

    @Test
    void getTest_queryParams_icaoNumber_blank() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).icaoNumber(" "));
    }

    @Test
    void getTest_queryParams_limit() {
        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .limit(LIMIT)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("limit", String.valueOf(LIMIT)));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_LIMIT, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_queryParams_limit_zero() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).limit(0));
    }

    @Test
    void getTest_queryParams_limit_lessThanZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).limit(-1));
    }

    @Test
    void getTest_queryParams_type() {
        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .type(DEPARTURE)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("type", DEPARTURE.textRepresentation));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_TYPE, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_queryParams_type_null() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).type(null));
    }

    @Test
    void getTest_queryParams_status() {
        ScheduledFlightRequest request = new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .status(SCHEDULED)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("status", SCHEDULED.textRepresentation));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_STATUS, ScheduledFlightAE[].class);
    }

    @Test
    void getTest_queryParams_status_null() {
        assertThrows(IllegalArgumentException.class, () ->
                new ScheduledFlightRequest.Builder(BASE_URL, API_KEY, restTemplate).status(null));
    }
}