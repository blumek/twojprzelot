package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.web.client.RestTemplate;

@EqualsAndHashCode(callSuper = true)
class CityRequest extends Request<CityAE> {
    private static final String CITY_RESOURCE_URL = "/cityDatabase";
    private static final String IATA_CODE = "codeIataCity";

    CityRequest(String url, String apiKey, RestTemplate restTemplate) {
        super(CityAE[].class, url, apiKey, restTemplate);
    }

    static class Builder {
        private final CityRequest cityRequest;

        Builder(String baseUrl, String apiKey, RestTemplate restTemplate) {
            cityRequest = new CityRequest(getFullResourceUrl(baseUrl), apiKey, restTemplate);
        }

        public Builder iataCode(@NonNull String iataCode) {
            if (iataCode.isBlank())
                throw new IllegalArgumentException();

            cityRequest.queryParams.put(IATA_CODE, iataCode);
            return this;
        }

        public CityRequest create() {
            return cityRequest;
        }

        private String getFullResourceUrl(String baseUrl) {
            return baseUrl + CITY_RESOURCE_URL;
        }
    }
}
