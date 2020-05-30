package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.valueOf;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.FlightRequestBuilder.QueryKey.*;


final class FlightRequestBuilder extends AviationEdgeRequestBuilder<FlightAE> {
    static final String FLIGHT_RESOURCE_URI = "/flights";

    FlightRequestBuilder(String baseUrl, String apiKey, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(FlightAE[].class, baseUrl, apiKey, restTemplate, objectMapper);
    }

    @Override
    String getResourceUrl(String baseUrl) {
        return baseUrl + FLIGHT_RESOURCE_URI;
    }

    public FlightRequestBuilder limit(long limit) {
        if (limit <= 0)
            throw new IllegalArgumentException("Limit value must be higher that zero");

        request.addQueryParameter(LIMIT.getKey(), valueOf(limit));
        return this;
    }

    public FlightRequestBuilder status(@NonNull Status status) {
        request.addQueryParameter(STATUS.getKey(), status.getValue());
        return this;
    }

    public FlightRequestBuilder iataNumber(@NonNull String iataNumber) {
        if (iataNumber.isBlank())
            throw new IllegalArgumentException("Iata Number cannot be empty");

        request.addQueryParameter(FLIGHT_IATA_NUMBER.getKey(), iataNumber);
        return this;
    }

    public FlightRequestBuilder icaoNumber(@NonNull String icaoNumber) {
        if (icaoNumber.isBlank())
            throw new IllegalArgumentException("Icao Number cannot be empty");

        request.addQueryParameter(FLIGHT_ICAO_NUMBER.getKey(), icaoNumber);
        return this;
    }

    @Override
    public AviationEdgeRequest<FlightAE> build() {
        return request;
    }

    @RequiredArgsConstructor
    @Getter
    enum QueryKey {
        LIMIT("limit"),
        STATUS("status"),
        FLIGHT_IATA_NUMBER("flightIata"),
        FLIGHT_ICAO_NUMBER("flightIcao");

        private final String key;
    }

    @RequiredArgsConstructor
    @Getter
    enum Status {
        STARTED("started"),
        EN_ROUTE("en-route"),
        LANDED("landed"),
        UNKNOWN("unknown");

        private final String value;
    }
}