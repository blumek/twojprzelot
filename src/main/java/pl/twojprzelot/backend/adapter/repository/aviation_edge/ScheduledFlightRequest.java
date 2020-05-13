package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@EqualsAndHashCode(callSuper = true)
final class ScheduledFlightRequest extends Request<ScheduledFlightAE> {
    private static final String SCHEDULED_FLIGHT_RESOURCE_URL = "/timetable";
    private static final String LIMIT = "limit";
    private static final String TYPE = "type";
    private static final String STATUS = "status";
    private static final String FLIGHT_IATA_NUMBER = "flight_iata";
    private static final String FLIGHT_ICAO_NUMBER = "flight_icao";

    ScheduledFlightRequest(String url, String apiKey, RestTemplate restTemplate) {
        super(ScheduledFlightAE[].class, url, apiKey, restTemplate);
    }

    static class Builder {
        private final ScheduledFlightRequest scheduledFlightRequest;

        Builder(String baseUrl, String apiKey, RestTemplate restTemplate) {
            scheduledFlightRequest = new ScheduledFlightRequest(getFullResourceUrl(baseUrl), apiKey, restTemplate);
        }

        public Builder limit(long limit) {
            if (limit <= 0)
                throw new IllegalArgumentException();

            scheduledFlightRequest.queryParams.put(LIMIT, String.valueOf(limit));
            return this;
        }

        public Builder type(@NonNull Type type) {
            scheduledFlightRequest.queryParams.put(TYPE, type.textRepresentation);
            return this;
        }

        public Builder status(@NonNull Status status) {
            scheduledFlightRequest.queryParams.put(STATUS, status.textRepresentation);
            return this;
        }

        public Builder iataNumber(@NonNull String iataNumber) {
            if (iataNumber.isBlank())
                throw new IllegalArgumentException();

            scheduledFlightRequest.queryParams.put(FLIGHT_IATA_NUMBER, iataNumber);
            return this;
        }

        public Builder icaoNumber(@NonNull String icaoNumber) {
            if (icaoNumber.isBlank())
                throw new IllegalArgumentException();

            scheduledFlightRequest.queryParams.put(FLIGHT_ICAO_NUMBER, icaoNumber);
            return this;
        }

        public ScheduledFlightRequest create() {
            return scheduledFlightRequest;
        }

        private String getFullResourceUrl(String baseUrl) {
            return baseUrl + SCHEDULED_FLIGHT_RESOURCE_URL;
        }
    }

    @RequiredArgsConstructor
    enum Type {
        DEPARTURE("departure"),
        ARRIVAL("arrival");

        final String textRepresentation;

    }

    @RequiredArgsConstructor
    enum Status {
        LANDED("landed"),
        SCHEDULED("scheduled"),
        CANCELLED("cancelled"),
        ACTIVE("active"),
        INCIDENT("incident"),
        DIVERTED("diverted"),
        REDIRECTED("redirected"),
        UNKNOWN("unknown");

        final String textRepresentation;

    }
}
