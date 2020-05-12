package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;

@RequiredArgsConstructor
public class FindScheduledFlight {
    private final ScheduledFlightImmutableRepository repository;

    public List<ScheduledFlight> findAllByFlightIdentifier(String flightIdentifier) {
        List<ScheduledFlight> scheduledFlightsByIataNumber = repository.findAllByIataNumber(flightIdentifier);
        if (!scheduledFlightsByIataNumber.isEmpty())
            return scheduledFlightsByIataNumber;

        return repository.findAllByIcaoNumber(flightIdentifier);
    }
}
