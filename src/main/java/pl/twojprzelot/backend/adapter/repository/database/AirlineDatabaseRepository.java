package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.port.AirlineMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
final class AirlineDatabaseRepository implements AirlineMutableRepository {
    private final AirlineSpringRepository repository;

    @Override
    public List<Airline> findAll() {
        return repository.findAll().stream()
                .map(AirlineEntity::toAirline)
                .collect(toList());
    }

    @Override
    public Optional<Airline> findByIataCode(@NonNull String iataCode) {
        return repository.findByIataCode(iataCode)
                .map(AirlineEntity::toAirline);
    }

    @Override
    public Optional<Airline> findByIcaoCode(@NonNull String icaoCode) {
        return repository.findByIcaoCode(icaoCode)
                .map(AirlineEntity::toAirline);
    }

    @Override
    public Airline create(@NonNull Airline airline) {
        AirlineEntity airlineToCreate = AirlineEntity.from(airline);
        AirlineEntity createdAirline = repository.save(airlineToCreate);
        return createdAirline.toAirline();
    }
}
