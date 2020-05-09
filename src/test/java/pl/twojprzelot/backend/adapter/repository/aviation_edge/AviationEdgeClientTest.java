package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AviationEdgeClientTest {
    private static final String URL = "https://aviation-edge.com/v2/public";
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

        CountryRequest expectedCountryRequest = new CountryRequest.Builder(URL, API_KEY, new RestTemplate())
                .create();

        assertEquals(expectedCountryRequest, countryRequest);
    }

    @Test
    void createCityRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY);
        CityRequest cityRequest = aviationEdgeClient.createCityRequest()
                .create();

        CityRequest expectedCityRequest = new CityRequest.Builder(URL, API_KEY, new RestTemplate())
                .create();

        assertEquals(expectedCityRequest, cityRequest);
    }

    @Test
    void createAirportRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY);
        AirportRequest airportRequest = aviationEdgeClient.createAirportRequest()
                .create();

        AirportRequest expectedAirportRequest = new AirportRequest.Builder(URL, API_KEY, new RestTemplate())
                .create();

        assertEquals(expectedAirportRequest, airportRequest);
    }

    @Test
    void createAirlineRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY);
        AirlineRequest airlineRequest = aviationEdgeClient.createAirlineRequest()
                .create();

        AirlineRequest expectedAirlineRequest = new AirlineRequest.Builder(URL, API_KEY, new RestTemplate())
                .create();

        assertEquals(expectedAirlineRequest, airlineRequest);
    }

    @Test
    void createScheduledFlightRequestTest() {
        AviationEdgeClient aviationEdgeClient = new AviationEdgeClient(API_KEY);
        ScheduledFlightRequest scheduledFlightRequest = aviationEdgeClient.createScheduledFlightRequest()
                .create();

        ScheduledFlightRequest expectedScheduledFlightRequest = new ScheduledFlightRequest.Builder(URL, API_KEY, new RestTemplate())
                .create();

        assertEquals(expectedScheduledFlightRequest, scheduledFlightRequest);
    }
}