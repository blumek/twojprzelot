package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

public interface ScheduledFlightMutableRepository extends ScheduledFlightImmutableRepository {
    ScheduledFlight create(ScheduledFlight scheduledFlight);
    ScheduledFlight update(ScheduledFlight scheduledFlight);
}
