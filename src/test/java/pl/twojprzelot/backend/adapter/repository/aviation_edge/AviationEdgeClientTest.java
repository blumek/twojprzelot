package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AviationEdgeClientTest {
    private static final String URL = "https://aviation-edge.com/v2/public";
    private static final String API_KEY = "API_KEY";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Test
    void constructorTest_nullKey() {
        assertThrows(NullPointerException.class,
                () -> new AviationEdgeClient(null, REST_TEMPLATE, OBJECT_MAPPER));
    }

    @Test
    void constructorTest_blankKey() {
        assertThrows(IllegalArgumentException.class,
                () -> new AviationEdgeClient("  ", REST_TEMPLATE, OBJECT_MAPPER));
    }

    @Test
    void createCountryRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
        AviationEdgeRequest<CountryAE> countryRequest = aviationEdgeClient.createCountryRequest()
                .build();

        AviationEdgeRequest<CountryAE> expectedCountryRequest =
                new CountryRequestBuilder(URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER)
                .build();

        assertEquals(expectedCountryRequest, countryRequest);
    }

    @Test
    void createCityRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
        AviationEdgeRequest<CityAE> cityRequest = aviationEdgeClient.createCityRequest().build();

        AviationEdgeRequest<CityAE> expectedCityRequest =
                new CityRequestBuilder(URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER)
                .build();

        assertEquals(expectedCityRequest, cityRequest);
    }

    @Test
    void createAirportRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
        AviationEdgeRequest<AirportAE> airportRequest = aviationEdgeClient.createAirportRequest()
                .build();

        AviationEdgeRequest<AirportAE> expectedAirportRequest =
                new AirportRequestBuilder(URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER)
                .build();

        assertEquals(expectedAirportRequest, airportRequest);
    }

    @Test
    void createAirlineRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
        AviationEdgeRequest<AirlineAE> airlineRequest = aviationEdgeClient.createAirlineRequest()
                .build();

        AviationEdgeRequest<AirlineAE> expectedAirlineRequest =
                new AirlineRequestBuilder(URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER)
                .build();

        assertEquals(expectedAirlineRequest, airlineRequest);
    }

    @Test
    void createScheduledFlightRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY, REST_TEMPLATE, OBJECT_MAPPER);
        AviationEdgeRequest<ScheduledFlightAE> scheduledFlightRequest = aviationEdgeClient.createScheduledFlightRequest()
                .build();

        AviationEdgeRequest<ScheduledFlightAE> expectedScheduledFlightRequest =
                new ScheduledFlightRequestBuilder(URL, API_KEY, REST_TEMPLATE, OBJECT_MAPPER)
                .build();

        assertEquals(expectedScheduledFlightRequest, scheduledFlightRequest);
    }
}