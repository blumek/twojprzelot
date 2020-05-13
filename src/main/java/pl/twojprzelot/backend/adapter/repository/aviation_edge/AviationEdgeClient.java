package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import org.springframework.web.client.RestTemplate;

final class AviationEdgeClient {
    private static final String URL = "https://aviation-edge.com/v2/public";

    private final String apiKey;

    public AviationEdgeClient(@NonNull String apiKey) {
        if (apiKey.isBlank())
            throw new IllegalArgumentException();

        this.apiKey = apiKey;
    }

    public CountryRequest.Builder createCountryRequest() {
        return new CountryRequest.Builder(URL, apiKey, new RestTemplate());
    }

    public CityRequest.Builder createCityRequest() {
        return new CityRequest.Builder(URL, apiKey, new RestTemplate());
    }

    public AirportRequest.Builder createAirportRequest() {
        return new AirportRequest.Builder(URL, apiKey, new RestTemplate());
    }

    public AirlineRequest.Builder createAirlineRequest() {
        return new AirlineRequest.Builder(URL, apiKey, new RestTemplate());
    }

    public ScheduledFlightRequest.Builder createScheduledFlightRequest() {
        return new ScheduledFlightRequest.Builder(URL, apiKey, new RestTemplate());
    }
}
