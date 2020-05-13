package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.web.client.RestTemplate;


@EqualsAndHashCode(callSuper = true)
final class AirlineRequest extends Request<AirlineAE> {
    private static final String AIRLINE_RESOURCE_URL = "/airlineDatabase";
    private static final String IATA_CODE = "codeIataAirline";

    AirlineRequest(String url, String apiKey, RestTemplate restTemplate) {
        super(AirlineAE[].class, url, apiKey, restTemplate);
    }

    static class Builder {
        private final AirlineRequest airlineRequest;

        Builder(String baseUrl, String apiKey, RestTemplate restTemplate) {
            airlineRequest = new AirlineRequest(getFullResourceUrl(baseUrl), apiKey, restTemplate);
        }

        public Builder iataCode(@NonNull String iataCode) {
            if (iataCode.isBlank())
                throw new IllegalArgumentException();

            airlineRequest.queryParams.put(IATA_CODE, iataCode);
            return this;
        }

        public AirlineRequest create() {
            return airlineRequest;
        }

        private String getFullResourceUrl(String baseUrl) {
            return baseUrl + AIRLINE_RESOURCE_URL;
        }
    }
}
