package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.port.AirlineMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Component
@RequiredArgsConstructor
class AirlineDatabaseRepository implements AirlineMutableRepository {
    private final AirlineSpringRepository repository;

    @Override
    public List<Airline> findAll() {
        return repository.findAll().stream()
                .map(AirlineEntity::toAirline)
                .collect(toList());
    }

    @Override
    public List<Airline> findAllByIataCode(@NonNull String iataCode) {
        return repository.findAllByIataCode(iataCode)
                .stream()
                .map(AirlineEntity::toAirline)
                .collect(toList());
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

    @Transactional
    @Override
    public List<Airline> overrideAll(@NonNull Iterable<Airline> airlines) {
        removeAllAndFlush();

        List<AirlineEntity> airlinesToCreate = stream(airlines.spliterator(), false)
                .map(AirlineEntity::from)
                .collect(toList());

        List<AirlineEntity> createdAirlines = repository.saveAll(airlinesToCreate);

        return createdAirlines.stream()
                .map(AirlineEntity::toAirline)
                .collect(toList());
    }

    private void removeAllAndFlush() {
        repository.deleteAll();
        repository.flush();
    }

    @Override
    public Airline update(@NonNull Airline airline) {
        if (!hasIdentifier(airline))
            throw new IllegalArgumentException("Identifier not valid");

        AirlineEntity airlineToUpdate = AirlineEntity.from(airline);
        AirlineEntity updatedAirline = repository.save(airlineToUpdate);
        return updatedAirline.toAirline();
    }

    private boolean hasIdentifier(Airline airline) {
        return airline.getId() > 0;
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
