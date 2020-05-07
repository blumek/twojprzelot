package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.port.AirportRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class AirportAERepository implements AirportRepository {
    private final AviationEdgeClient client;

    @Override
    public Optional<Airport> findByIataCode(String iataCode) {
        AirportRequest airportRequest = client.createAirportRequest()
                .iataCode(iataCode)
                .create();

        return airportRequest.get().stream()
                .findFirst()
                .map(AirportAE::toAirport);
    }

    @Override
    public Optional<Airport> findByIcaoCode(String icaoCode) {
        AirportRequest airportRequest = client.createAirportRequest()
                .create();

        return airportRequest.get().stream()
                .filter(airportAE -> airportAE.getIcaoCode().equalsIgnoreCase(icaoCode))
                .findFirst()
                .map(AirportAE::toAirport);
    }

    @Override
    public List<Airport> findAll() {
        AirportRequest airportRequest = client.createAirportRequest()
                .create();

        return airportRequest.get().stream()
                .map(AirportAE::toAirport)
                .collect(Collectors.toList());
    }
}
