package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;

@RequiredArgsConstructor
abstract class ForwardingScheduledFlightDatabaseRepository implements ScheduledFlightImmutableRepository {
    final ScheduledFlightImmutableRepository repository;

    @Override
    public List<ScheduledFlight> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ScheduledFlight> findAllByIataNumber(String iataNumber) {
        return repository.findAllByIataNumber(iataNumber);
    }

    @Override
    public List<ScheduledFlight> findAllByIcaoNumber(String icaoNumber) {
        return repository.findAllByIcaoNumber(icaoNumber);
    }
}
