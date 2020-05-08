package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.port.AirlineRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class AirlineAERepository implements AirlineRepository {
    private final AviationEdgeClient client;

    @Override
    public Optional<Airline> findByIataCode(String iataCode) {
        AirlineRequest airlineRequest = client.createAirlineRequest()
                .iataCode(iataCode)
                .create();

        return airlineRequest.get().stream()
                .findFirst()
                .map(AirlineAE::toAirline);
    }

    @Override
    public Optional<Airline> findByIcaoCode(String icaoCode) {
        AirlineRequest airlineRequest = client.createAirlineRequest()
                .create();

        return airlineRequest.get().stream()
                .filter(airlineAE -> airlineAE.getIcaoCode().equalsIgnoreCase(icaoCode))
                .findFirst()
                .map(AirlineAE::toAirline);
    }

    @Override
    public List<Airline> findAll() {
        AirlineRequest airlineRequest = client.createAirlineRequest()
                .create();

        return airlineRequest.get().stream()
                .map(AirlineAE::toAirline)
                .collect(Collectors.toList());
    }
}
