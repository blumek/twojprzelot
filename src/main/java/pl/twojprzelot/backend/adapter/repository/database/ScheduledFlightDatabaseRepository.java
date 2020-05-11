package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ScheduledFlightDatabaseRepository implements ScheduledFlightRepository {
    private final ScheduledFlightSpringRepository repository;

    @Override
    public List<ScheduledFlight> findAll() {
        return repository.findAll().stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIataNumber(String iataNumber) {
        return repository.findAllByFlightIdentifier_IataNumber(iataNumber).stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIcaoNumber(String icaoNumber) {
        return repository.findAllByFlightIdentifier_IcaoNumber(icaoNumber).stream()
                .map(ScheduledFlightEntity::toScheduledFlight)
                .collect(toList());
    }
}
