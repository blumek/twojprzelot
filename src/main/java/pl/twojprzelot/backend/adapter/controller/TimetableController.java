package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.adapter.controller.model.ScheduledFlightWeb;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.Optional;

@RequiredArgsConstructor
public class TimetableController {
    private final FindScheduledFlight findScheduledFlight;

    public Optional<ScheduledFlightWeb> findByIdentifier(String identifier) {
        return findScheduledFlight.findByFlightIdentifier(identifier)
                .map(ScheduledFlightWeb::from);
    }
}
