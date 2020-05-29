package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.valueOf;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequestBuilder.QueryKey.*;


final class ScheduledFlightRequestBuilder extends AviationEdgeRequestBuilder<ScheduledFlightAE> {
    static final String SCHEDULED_FLIGHT_RESOURCE_URI = "/timetable";

    ScheduledFlightRequestBuilder(String baseUrl, String apiKey, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(ScheduledFlightAE[].class, baseUrl, apiKey, restTemplate, objectMapper);
    }

    @Override
    String getResourceUrl(String baseUrl) {
        return baseUrl + SCHEDULED_FLIGHT_RESOURCE_URI;
    }

    public ScheduledFlightRequestBuilder limit(long limit) {
        if (limit <= 0)
            throw new IllegalArgumentException("Limit value must be higher that zero");

        request.addQueryParameter(LIMIT.getKey(), valueOf(limit));
        return this;
    }

    public ScheduledFlightRequestBuilder type(@NonNull Type type) {
        request.addQueryParameter(TYPE.getKey(), type.getValue());
        return this;
    }

    public ScheduledFlightRequestBuilder status(@NonNull Status status) {
        request.addQueryParameter(STATUS.getKey(), status.getValue());
        return this;
    }

    public ScheduledFlightRequestBuilder iataNumber(@NonNull String iataNumber) {
        if (iataNumber.isBlank())
            throw new IllegalArgumentException("Iata Number cannot be empty");

        request.addQueryParameter(FLIGHT_IATA_NUMBER.getKey(), iataNumber);
        return this;
    }

    public ScheduledFlightRequestBuilder icaoNumber(@NonNull String icaoNumber) {
        if (icaoNumber.isBlank())
            throw new IllegalArgumentException("Icao Number cannot be empty");

        request.addQueryParameter(FLIGHT_ICAO_NUMBER.getKey(), icaoNumber);
        return this;
    }

    @Override
    public AviationEdgeRequest<ScheduledFlightAE> build() {
        return request;
    }

    @RequiredArgsConstructor
    @Getter
    enum QueryKey {
        LIMIT("limit"),
        TYPE("type"),
        STATUS("status"),
        FLIGHT_IATA_NUMBER("flight_iata"),
        FLIGHT_ICAO_NUMBER("flight_icao");

        private final String key;
    }

    @RequiredArgsConstructor
    @Getter
    enum Type {
        DEPARTURE("departure"),
        ARRIVAL("arrival");

        private final String value;
    }

    @RequiredArgsConstructor
    @Getter
    enum Status {
        LANDED("landed"),
        SCHEDULED("scheduled"),
        CANCELLED("cancelled"),
        ACTIVE("active"),
        INCIDENT("incident"),
        DIVERTED("diverted"),
        REDIRECTED("redirected"),
        UNKNOWN("unknown");

        private final String value;
    }
}