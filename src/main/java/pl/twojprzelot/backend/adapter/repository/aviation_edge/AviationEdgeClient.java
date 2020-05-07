package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.springframework.web.client.RestTemplate;

import static org.apache.commons.lang3.StringUtils.isBlank;

class AviationEdgeClient {
    private static final String URL = "https://aviation-edge.com/v2/public";

    private final String apiKey;

    public AviationEdgeClient(String apiKey) {
        if (isBlank(apiKey))
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
}
