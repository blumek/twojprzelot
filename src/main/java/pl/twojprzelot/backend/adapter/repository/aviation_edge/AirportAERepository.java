package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.port.AirportImmutableRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
final class AirportAERepository implements AirportImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<Airport> findAll() {
        AviationEdgeRequest<AirportAE> airportRequest = client.createAirportRequest()
                .build();

        return airportRequest.get()
                .stream()
                .map(AirportAE::toAirport)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Airport> findByIataCode(@NonNull String iataCode) {
        AviationEdgeRequest<AirportAE> airportRequest = client.createAirportRequest()
                .iataCode(iataCode)
                .build();

        return airportRequest.get()
                .stream()
                .findFirst()
                .map(AirportAE::toAirport);
    }

    @Override
    public Optional<Airport> findByIcaoCode(@NonNull String icaoCode) {
        AviationEdgeRequest<AirportAE> airportRequest = client.createAirportRequest()
                .build();

        return airportRequest.get()
                .stream()
                .filter(airportAE -> airportAE.getIcaoCode().equalsIgnoreCase(icaoCode))
                .findFirst()
                .map(AirportAE::toAirport);
    }
}
