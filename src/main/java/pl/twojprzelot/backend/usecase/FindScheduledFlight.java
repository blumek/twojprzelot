package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingLong;
import static pl.twojprzelot.backend.utils.FlightTime.millisUntilNow;

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
                .filter(scheduledFlight -> scheduledFlight.getDeparture() != null)
                .min(comparingLong(this::untilNow));
    }

    private long untilNow(ScheduledFlight scheduledFlight) {
        return millisUntilNow(scheduledFlight.getDeparture())
                .orElse(Long.MAX_VALUE);
    }

}
