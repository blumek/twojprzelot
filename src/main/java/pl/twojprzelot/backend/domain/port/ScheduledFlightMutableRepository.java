package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import java.util.List;

public interface ScheduledFlightMutableRepository extends ScheduledFlightImmutableRepository {
    ScheduledFlight create(ScheduledFlight scheduledFlight);
    List<ScheduledFlight> overrideAll(Iterable<ScheduledFlight> scheduledFlights);
    ScheduledFlight update(ScheduledFlight scheduledFlight);
    void removeAll();
}
