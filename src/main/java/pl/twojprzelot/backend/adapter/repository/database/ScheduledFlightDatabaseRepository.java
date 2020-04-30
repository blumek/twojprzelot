package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.adapter.repository.database.model.ScheduledFlightEntity;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class ScheduledFlightDatabaseRepository implements ScheduledFlightRepository {
    private final ScheduledFlightSpringRepository scheduledFlightSpringRepository;

    @Override
    public Optional<ScheduledFlight> findByIataNumber(String iataNumber) {
        return scheduledFlightSpringRepository.findByFlightIdentifier_IataNumber(iataNumber).stream()
                .findFirst()
                .map(ScheduledFlightEntity::toScheduledFlight);
    }

    @Override
    public Optional<ScheduledFlight> findByIcaoNumber(String icaoNumber) {
        return scheduledFlightSpringRepository.findByFlightIdentifier_IcaoNumber(icaoNumber).stream()
                .findFirst()
                .map(ScheduledFlightEntity::toScheduledFlight);
    }

    @Override
    public ScheduledFlight create(ScheduledFlight scheduledFlight) {
        ScheduledFlightEntity scheduledFlightEntity = ScheduledFlightEntity.from(scheduledFlight);
        ScheduledFlightEntity savedScheduledFlightEntity = scheduledFlightSpringRepository.save(scheduledFlightEntity);
        return savedScheduledFlightEntity.toScheduledFlight();
    }
}
