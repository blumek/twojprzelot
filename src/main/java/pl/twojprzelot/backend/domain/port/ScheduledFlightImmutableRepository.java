package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import java.util.List;

public interface ScheduledFlightImmutableRepository {
    List<ScheduledFlight> findAll();
    List<ScheduledFlight> findAllByIataNumber(String iataNumber);
    List<ScheduledFlight> findAllByIcaoNumber(String icaoNumber);
}
