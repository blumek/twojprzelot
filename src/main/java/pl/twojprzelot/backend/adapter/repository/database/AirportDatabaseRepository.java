package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.port.AirportRepository;

import java.util.Optional;

@RequiredArgsConstructor
class AirportDatabaseRepository implements AirportRepository {
    private final AirportSpringRepository repository;

    @Override
    public Optional<Airport> findByIataCode(String iataCode) {
        return repository.findByIataCode(iataCode)
                .map(AirportEntity::toAirport);
    }

    @Override
    public Optional<Airport> findByIcaoCode(String icaoCode) {
        return repository.findByIcaoCode(icaoCode)
                .map(AirportEntity::toAirport);
    }
}
