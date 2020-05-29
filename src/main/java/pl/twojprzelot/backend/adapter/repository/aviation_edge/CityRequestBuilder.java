package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import static pl.twojprzelot.backend.adapter.repository.aviation_edge.CityRequestBuilder.QueryParameter.IATA_CODE;


final class CityRequestBuilder extends AviationEdgeRequestBuilder<CityAE> {
    static final String CITY_RESOURCE_URI = "/cityDatabase";

    CityRequestBuilder(String baseUrl, String apiKey, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(CityAE[].class, baseUrl, apiKey, restTemplate, objectMapper);
    }

    @Override
    String getResourceUrl(String baseUrl) {
        return baseUrl + CITY_RESOURCE_URI;
    }

    public CityRequestBuilder iataCode(@NonNull String iataCode) {
        if (iataCode.isBlank())
            throw new IllegalArgumentException("Iata Code cannot be empty");

        request.addQueryParameter(IATA_CODE.getKey(), iataCode);
        return this;
    }

    @Override
    public AviationEdgeRequest<CityAE> build() {
        return request;
    }

    @RequiredArgsConstructor
    @Getter
    enum QueryParameter {
        IATA_CODE("codeIataCity");

        private final String key;
    }
}