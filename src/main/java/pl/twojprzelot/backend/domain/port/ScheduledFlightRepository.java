package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import java.util.Optional;

public interface ScheduledFlightRepository {
    Optional<ScheduledFlight> findByIataNumber(String iataNumber);
    Optional<ScheduledFlight> findByIcaoNumber(String icaoNumber);
    ScheduledFlight create(ScheduledFlight scheduledFlight);
}
