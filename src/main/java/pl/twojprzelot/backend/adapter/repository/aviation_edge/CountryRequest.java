package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.EqualsAndHashCode;
import org.springframework.web.client.RestTemplate;

import static org.apache.commons.lang3.StringUtils.isBlank;

@EqualsAndHashCode(callSuper = true)
class CountryRequest extends Request<CountryAE> {
    private static final String COUNTRY_RESOURCE_URL = "/countryDatabase";
    private static final String ISO_2_CODE = "codeIso2Country";

    CountryRequest(String url, String apiKey, RestTemplate restTemplate) {
        super(CountryAE[].class, url, apiKey, restTemplate);
    }

    static class Builder {
        private final CountryRequest countryRequest;

        Builder(String baseUrl, String apiKey, RestTemplate restTemplate) {
            countryRequest = new CountryRequest(getFullResourceUrl(baseUrl), apiKey, restTemplate);
        }

        public Builder iso2Code(String iso2Code) {
            if (isBlank(iso2Code))
                throw new IllegalArgumentException();

            countryRequest.queryParams.put(ISO_2_CODE, iso2Code);
            return this;
        }

        public CountryRequest create() {
            return countryRequest;
        }

        private String getFullResourceUrl(String baseUrl) {
            return baseUrl + COUNTRY_RESOURCE_URL;
        }
    }
}