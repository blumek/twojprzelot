package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightMutableRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Component
@RequiredArgsConstructor
class ScheduledFlightDatabaseRepository implements ScheduledFlightMutableRepository {
    private final ScheduledFlightSpringRepository repository;

    @Override
    public List<ScheduledFlight> findAll() {
        return repository.findAll().stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIataNumber(@NonNull String iataNumber) {
        return repository.findAllByFlightIdentifierIataNumber(iataNumber).stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIcaoNumber(@NonNull String icaoNumber) {
        return repository.findAllByFlightIdentifierIcaoNumber(icaoNumber).stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public ScheduledFlight create(@NonNull ScheduledFlight scheduledFlight) {
        ScheduledFlightEntity scheduledFlightToCreate = ScheduledFlightEntity.from(scheduledFlight);
        ScheduledFlightEntity createdScheduledFlight = repository.save(scheduledFlightToCreate);
        return createdScheduledFlight.toScheduledFlight();
    }

    @Transactional
    @Override
    public List<ScheduledFlight> overrideAll(@NonNull Iterable<ScheduledFlight> scheduledFlights) {
        removeAllAndFlush();

        List<ScheduledFlightEntity> scheduledFlightsToCreate = stream(scheduledFlights.spliterator(), false)
                .map(ScheduledFlightEntity::from)
                .collect(toList());

        List<ScheduledFlightEntity> createdScheduledFlights = repository.saveAll(scheduledFlightsToCreate);

        return createdScheduledFlights.stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    private void removeAllAndFlush() {
        repository.deleteAll();
        repository.flush();
    }

    @Override
    public ScheduledFlight update(@NonNull ScheduledFlight scheduledFlight) {
        if (!hasIdentifier(scheduledFlight))
            throw new IllegalArgumentException("Identifier not valid");

        ScheduledFlightEntity scheduledFlightToUpdate = ScheduledFlightEntity.from(scheduledFlight);
        ScheduledFlightEntity updatedScheduledFlight = repository.save(scheduledFlightToUpdate);
        return updatedScheduledFlight.toScheduledFlight();
    }

    private boolean hasIdentifier(ScheduledFlight scheduledFlight) {
        return scheduledFlight.getId() > 0;
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
