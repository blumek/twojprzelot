package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightMutableRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ScheduledFlightDatabaseRepository implements ScheduledFlightMutableRepository {
    private final ScheduledFlightSpringRepository repository;

    @Override
    public List<ScheduledFlight> findAll() {
        return repository.findAll().stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIataNumber(@NonNull String iataNumber) {
        return repository.findAllByFlightIdentifier_IataNumber(iataNumber).stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIcaoNumber(@NonNull String icaoNumber) {
        return repository.findAllByFlightIdentifier_IcaoNumber(icaoNumber).stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public ScheduledFlight create(@NonNull ScheduledFlight scheduledFlight) {
        ScheduledFlightEntity scheduledFlightToCreate = ScheduledFlightEntity.from(scheduledFlight);
        ScheduledFlightEntity createdScheduledFlight = repository.save(scheduledFlightToCreate);
        return createdScheduledFlight.toScheduledFlight();
    }
}
