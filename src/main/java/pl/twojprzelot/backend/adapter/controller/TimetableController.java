package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.adapter.controller.model.ScheduledFlightWeb;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class TimetableController {
    private final FindScheduledFlight findScheduledFlight;

    public List<ScheduledFlightWeb> findAllByFlightIdentifier(String identifier) {
        return findScheduledFlight.findAllByFlightIdentifier(identifier).stream()
                .map(ScheduledFlightWeb::from)
                .collect(toList());
    }
}
