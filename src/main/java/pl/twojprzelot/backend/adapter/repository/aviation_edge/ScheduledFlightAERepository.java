package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;

import static java.lang.Long.MAX_VALUE;
import static java.util.stream.Collectors.toList;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequestBuilder.Type.DEPARTURE;

@Component
@RequiredArgsConstructor
final class ScheduledFlightAERepository implements ScheduledFlightImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<ScheduledFlight> findAll() {
        AviationEdgeRequest<ScheduledFlightAE> scheduledFlightRequest = client.createScheduledFlightRequest()
                .limit(MAX_VALUE)
                .type(DEPARTURE)
                .build();

        return scheduledFlightRequest.get()
                .stream()
                .map(ScheduledFlightAE::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIataNumber(@NonNull String iataNumber) {
        AviationEdgeRequest<ScheduledFlightAE> scheduledFlightRequest = client.createScheduledFlightRequest()
                .iataNumber(iataNumber)
                .build();

        return scheduledFlightRequest.get()
                .stream()
                .map(ScheduledFlightAE::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIcaoNumber(@NonNull String icaoNumber) {
        AviationEdgeRequest<ScheduledFlightAE> scheduledFlightRequest = client.createScheduledFlightRequest()
                .icaoNumber(icaoNumber)
                .build();

        return scheduledFlightRequest.get()
                .stream()
                .map(ScheduledFlightAE::toScheduledFlight)
                .collect(toList());
    }
}
