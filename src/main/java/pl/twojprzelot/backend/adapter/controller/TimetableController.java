package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.usecase.FindFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
final class TimetableController {
    private final FindScheduledFlight findScheduledFlight;
    private final FindFlight findFlight;

    public List<ScheduledFlightWeb> findAllByFlightIdentifier(String identifier) {
        return findScheduledFlight.findAllByFlightIdentifier(identifier)
                .stream()
                .map(ScheduledFlightWeb::from)
                .collect(toList());
    }
}
