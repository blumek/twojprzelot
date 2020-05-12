package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.web.client.RestTemplate;

@EqualsAndHashCode(callSuper = true)
class AirportRequest extends Request<AirportAE> {
    private static final String AIRPORT_RESOURCE_URL = "/airportDatabase";
    private static final String IATA_CODE = "codeIataAirport";

    AirportRequest(String url, String apiKey, RestTemplate restTemplate) {
        super(AirportAE[].class, url, apiKey, restTemplate);
    }

    static class Builder {
        private final AirportRequest airportRequest;

        Builder(String baseUrl, String apiKey, RestTemplate restTemplate) {
            airportRequest = new AirportRequest(getFullResourceUrl(baseUrl), apiKey, restTemplate);
        }

        public Builder iataCode(@NonNull String iataCode) {
            if (iataCode.isBlank())
                throw new IllegalArgumentException();

            airportRequest.queryParams.put(IATA_CODE, iataCode);
            return this;
        }

        public AirportRequest create() {
            return airportRequest;
        }

        private String getFullResourceUrl(String baseUrl) {
            return baseUrl + AIRPORT_RESOURCE_URL;
        }
    }
}
