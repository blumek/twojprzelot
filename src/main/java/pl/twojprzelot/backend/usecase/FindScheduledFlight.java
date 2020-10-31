package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

@RequiredArgsConstructor
public final class FindScheduledFlight {
    private final ScheduledFlightImmutableRepository repository;

    public List<ScheduledFlight> findAllByFlightIdentifier(String flightIdentifier) {
        List<ScheduledFlight> scheduledFlightsByIataNumber = repository.findAllByIataNumber(flightIdentifier);
        if (!scheduledFlightsByIataNumber.isEmpty())
            return scheduledFlightsByIataNumber;

        return repository.findAllByIcaoNumber(flightIdentifier);
    }

    public Optional<ScheduledFlight> findCurrentByFlightIdentifier(String flightIdentifier) {
        return findAllByFlightIdentifier(flightIdentifier)
                .stream()
                .min(comparing(scheduledFlight -> scheduledFlight.getDeparture().getEstimatedTime()));
    }
}
