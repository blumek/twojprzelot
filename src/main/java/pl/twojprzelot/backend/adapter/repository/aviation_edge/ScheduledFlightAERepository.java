package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;

import static java.lang.Long.MAX_VALUE;
import static java.util.stream.Collectors.toList;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequest.Type.DEPARTURE;

@RequiredArgsConstructor
class ScheduledFlightAERepository implements ScheduledFlightImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<ScheduledFlight> findAll() {
        ScheduledFlightRequest scheduledFlightRequest = client.createScheduledFlightRequest()
                .limit(MAX_VALUE)
                .type(DEPARTURE)
                .create();

        return scheduledFlightRequest.get().stream()
                .map(ScheduledFlightAE::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIataNumber(@NonNull String iataNumber) {
        ScheduledFlightRequest scheduledFlightRequest = client.createScheduledFlightRequest()
                .iataNumber(iataNumber)
                .create();

        return scheduledFlightRequest.get().stream()
                .map(ScheduledFlightAE::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIcaoNumber(@NonNull String icaoNumber) {
        ScheduledFlightRequest scheduledFlightRequest = client.createScheduledFlightRequest()
                .icaoNumber(icaoNumber)
                .create();

        return scheduledFlightRequest.get().stream()
                .map(ScheduledFlightAE::toScheduledFlight)
                .collect(toList());
    }
}
