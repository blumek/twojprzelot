package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class FindScheduledFlight {
    private final ScheduledFlightRepository scheduledFlightRepository;

    public Optional<ScheduledFlight> findByFlightIdentifier(String flightIdentifier) {
        return scheduledFlightRepository.findByIataNumber(flightIdentifier)
                .or(() -> scheduledFlightRepository.findByIcaoNumber(flightIdentifier));
    }
}
