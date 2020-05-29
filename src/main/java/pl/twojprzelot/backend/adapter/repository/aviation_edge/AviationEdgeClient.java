package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.web.client.RestTemplate;

final class AviationEdgeClient {
    private static final String URL = "https://aviation-edge.com/v2/public";

    private final String apiKey;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AviationEdgeClient(@NonNull String apiKey, RestTemplate restTemplate, ObjectMapper objectMapper) {
        if (apiKey.isBlank())
            throw new IllegalArgumentException();

        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public CountryRequestBuilder createCountryRequest() {
        return new CountryRequestBuilder(URL, apiKey, restTemplate, objectMapper);
    }

    public CityRequestBuilder createCityRequest() {
        return new CityRequestBuilder(URL, apiKey, restTemplate, objectMapper);
    }

    public AirportRequestBuilder createAirportRequest() {
        return new AirportRequestBuilder(URL, apiKey, restTemplate, objectMapper);
    }

    public AirlineRequestBuilder createAirlineRequest() {
        return new AirlineRequestBuilder(URL, apiKey, restTemplate, objectMapper);
    }

    public ScheduledFlightRequestBuilder createScheduledFlightRequest() {
        return new ScheduledFlightRequestBuilder(URL, apiKey, restTemplate, objectMapper);
    }
}
