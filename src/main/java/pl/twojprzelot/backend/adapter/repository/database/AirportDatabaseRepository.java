package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.port.AirportMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
final class AirportDatabaseRepository implements AirportMutableRepository {
    private final AirportSpringRepository repository;

    @Override
    public List<Airport> findAll() {
        return repository.findAll().stream()
                .map(AirportEntity::toAirport)
                .collect(toList());
    }

    @Override
    public Optional<Airport> findByIataCode(@NonNull String iataCode) {
        return repository.findByIataCode(iataCode)
                .map(AirportEntity::toAirport);
    }

    @Override
    public Optional<Airport> findByIcaoCode(@NonNull String icaoCode) {
        return repository.findByIcaoCode(icaoCode)
                .map(AirportEntity::toAirport);
    }

    @Override
    public Airport create(@NonNull Airport airport) {
        AirportEntity airportToCreate = AirportEntity.from(airport);
        AirportEntity createdAirport = repository.save(airportToCreate);
        return createdAirport.toAirport();
    }

    @Override
    public Airport update(@NonNull Airport airport) {
        if (!hasIdentifier(airport))
            throw new IllegalArgumentException("Identifier not valid");

        AirportEntity airportToUpdate = AirportEntity.from(airport);
        AirportEntity updatedAirport = repository.save(airportToUpdate);
        return updatedAirport.toAirport();
    }

    private boolean hasIdentifier(Airport airport) {
        return airport.getId() > 0;
    }
}
