package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.port.AirlineRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class AirlineDatabaseRepository implements AirlineRepository {
    private final AirlineSpringRepository repository;

    @Override
    public Optional<Airline> findByIataCode(String iataCode) {
        return repository.findByIataCode(iataCode)
                .map(AirlineEntity::toAirline);
    }

    @Override
    public Optional<Airline> findByIcaoCode(String icaoCode) {
        return repository.findByIcaoCode(icaoCode)
                .map(AirlineEntity::toAirline);
    }

    @Override
    public List<Airline> findAll() {
        return repository.findAll().stream()
                .map(AirlineEntity::toAirline)
                .collect(toList());
    }
}
