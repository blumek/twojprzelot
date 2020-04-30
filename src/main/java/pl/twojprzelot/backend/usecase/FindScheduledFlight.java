package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightRepository;

import java.util.List;

@RequiredArgsConstructor
public class FindScheduledFlight {
    private final ScheduledFlightRepository scheduledFlightRepository;

    public List<ScheduledFlight> findAllByFlightIdentifier(String flightIdentifier) {
        List<ScheduledFlight> scheduledFlightsByIataNumber = scheduledFlightRepository.findAllByIataNumber(flightIdentifier);
        if (!scheduledFlightsByIataNumber.isEmpty())
            return scheduledFlightsByIataNumber;

        return scheduledFlightRepository.findAllByIcaoNumber(flightIdentifier);
    }
}
