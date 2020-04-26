package pl.twojprzelot.backend.usecase;

import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightRepository;

import java.util.Optional;

public class FindScheduledFlight {
    private final ScheduledFlightRepository scheduledFlightRepository;

    public FindScheduledFlight(ScheduledFlightRepository scheduledFlightRepository) {
        this.scheduledFlightRepository = scheduledFlightRepository;
    }

    public Optional<ScheduledFlight> findByFlightIdentifier(String flightIdentifier) {
        return scheduledFlightRepository.findByIataNumber(flightIdentifier)
                .or(() -> scheduledFlightRepository.findByIcaoNumber(flightIdentifier));
    }
}
