package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
final class AirlineAERepository implements AirlineImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<Airline> findAll() {
        AirlineRequest airlineRequest = client.createAirlineRequest()
                .create();

        return airlineRequest.get().stream()
                .map(AirlineAE::toAirline)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Airline> findByIataCode(@NonNull String iataCode) {
        AirlineRequest airlineRequest = client.createAirlineRequest()
                .iataCode(iataCode)
                .create();

        return airlineRequest.get().stream()
                .findFirst()
                .map(AirlineAE::toAirline);
    }

    @Override
    public Optional<Airline> findByIcaoCode(@NonNull String icaoCode) {
        AirlineRequest airlineRequest = client.createAirlineRequest()
                .create();

        return airlineRequest.get().stream()
                .filter(airlineAE -> airlineAE.getIcaoCode().equalsIgnoreCase(icaoCode))
                .findFirst()
                .map(AirlineAE::toAirline);
    }
}
