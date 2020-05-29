package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import static pl.twojprzelot.backend.adapter.repository.aviation_edge.CountryRequestBuilder.QueryParameter.ISO_2_CODE;


final class CountryRequestBuilder extends AviationEdgeRequestBuilder<CountryAE> {
    static final String COUNTRY_RESOURCE_URI = "/countryDatabase";

    CountryRequestBuilder(String baseUrl, String apiKey, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(CountryAE[].class, baseUrl, apiKey, restTemplate, objectMapper);
    }

    @Override
    String getResourceUrl(String baseUrl) {
        return baseUrl + COUNTRY_RESOURCE_URI;
    }

    public CountryRequestBuilder iso2Code(@NonNull String iso2Code) {
        if (iso2Code.isBlank())
            throw new IllegalArgumentException("Iso2 Code cannot be empty");

        request.addQueryParameter(ISO_2_CODE.getKey(), iso2Code);
        return this;
    }

    @Override
    public AviationEdgeRequest<CountryAE> build() {
        return request;
    }

    @RequiredArgsConstructor
    @Getter
    enum QueryParameter {
        ISO_2_CODE("codeIso2Country");

        private final String key;
    }
}
