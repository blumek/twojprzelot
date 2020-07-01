package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
final class AirlineAERepository implements AirlineImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<Airline> findAll() {
        AviationEdgeRequest<AirlineAE> airlineRequest = client.createAirlineRequest()
                .build();

        return airlineRequest.get()
                .stream()
                .map(AirlineAE::toAirline)
                .collect(toList());
    }

    @Override
    public List<Airline> findAllByIataCode(@NonNull String iataCode) {
        AviationEdgeRequest<AirlineAE> airlineRequest = client.createAirlineRequest()
                .iataCode(iataCode)
                .build();

        return airlineRequest.get()
                .stream()
                .map(AirlineAE::toAirline)
                .collect(toList());
    }

    @Override
    public Optional<Airline> findByIcaoCode(@NonNull String icaoCode) {
        AviationEdgeRequest<AirlineAE> airlineRequest = client.createAirlineRequest()
                .build();

        return airlineRequest.get()
                .stream()
                .filter(airlineAE -> airlineAE.getIcaoCode().equalsIgnoreCase(icaoCode))
                .findFirst()
                .map(AirlineAE::toAirline);
    }
}
