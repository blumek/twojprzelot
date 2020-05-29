package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import static pl.twojprzelot.backend.adapter.repository.aviation_edge.AirlineRequestBuilder.QueryParameter.IATA_CODE;


final class AirlineRequestBuilder extends AviationEdgeRequestBuilder<AirlineAE> {
    static final String AIRLINE_RESOURCE_URI = "/airlineDatabase";

    AirlineRequestBuilder(String baseUrl, String apiKey, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(AirlineAE[].class, baseUrl, apiKey, restTemplate, objectMapper);
    }

    @Override
    String getResourceUrl(String baseUrl) {
        return baseUrl + AIRLINE_RESOURCE_URI;
    }

    public AirlineRequestBuilder iataCode(@NonNull String iataCode) {
        if (iataCode.isBlank())
            throw new IllegalArgumentException();

        request.addQueryParameter(IATA_CODE.getKey(), iataCode);
        return this;
    }

    @Override
    public AviationEdgeRequest<AirlineAE> build() {
        return request;
    }

    @RequiredArgsConstructor
    @Getter
    enum QueryParameter {
        IATA_CODE("codeIataAirline");

        private final String key;
    }
}
