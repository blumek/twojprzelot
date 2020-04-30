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
        return scheduledFlightSpringRepository.findDistinctTopByFlightIdentifier_IataNumber(iataNumber)
                .map(ScheduledFlightEntity::toScheduledFlight);
    }

    @Override
    public Optional<ScheduledFlight> findByIcaoNumber(String icaoNumber) {
        return scheduledFlightSpringRepository.findDistinctTopByFlightIdentifier_IcaoNumber(icaoNumber)
                .map(ScheduledFlightEntity::toScheduledFlight);
    }
}
