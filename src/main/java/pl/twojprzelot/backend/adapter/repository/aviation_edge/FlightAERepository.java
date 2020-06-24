package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
final class FlightAERepository implements FlightImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<Flight> findAll() {
        AviationEdgeRequest<FlightAE> flightRequest = client.createFlightRequest()
                .build();

        return flightRequest.get()
                .stream()
                .map(FlightAE::toFlight)
                .collect(toList());
    }
}
