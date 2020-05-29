package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import static pl.twojprzelot.backend.adapter.repository.aviation_edge.AirportRequestBuilder.QueryParameter.IATA_CODE;


final class AirportRequestBuilder extends AviationEdgeRequestBuilder<AirportAE> {
    static final String AIRPORT_RESOURCE_URI = "/airportDatabase";

    AirportRequestBuilder(String baseUrl, String apiKey, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(AirportAE[].class, baseUrl, apiKey, restTemplate, objectMapper);
    }

    @Override
    String getResourceUrl(String baseUrl) {
        return baseUrl + AIRPORT_RESOURCE_URI;
    }

    public AirportRequestBuilder iataCode(@NonNull String iataCode) {
        if (iataCode.isBlank())
            throw new IllegalArgumentException("Iata Code cannot be empty");

        request.addQueryParameter(IATA_CODE.getKey(), iataCode);
        return this;
    }

    @Override
    public AviationEdgeRequest<AirportAE> build() {
        return request;
    }

    @RequiredArgsConstructor
    @Getter
    enum QueryParameter {
        IATA_CODE("codeIataAirport");

        private final String key;
    }
}
