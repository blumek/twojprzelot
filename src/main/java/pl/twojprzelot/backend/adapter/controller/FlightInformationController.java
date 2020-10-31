package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.usecase.FindFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.Optional;

@Component
@RequiredArgsConstructor
final class FlightInformationController {
    private final FindFlight findFlight;
    private final FindScheduledFlight findScheduledFlight;

    public Optional<FlightInformationWeb> findCurrentByFlightIdentifier(String identifier) {
        Optional<FlightWeb> flight = currentFlightByIdentifier(identifier);
        Optional<ScheduledFlightWeb> scheduledFlight = currentScheduledFlightByIdentifier(identifier);
        return createFlightInformation(flight, scheduledFlight);
    }

    private Optional<FlightWeb> currentFlightByIdentifier(String identifier) {
        return findFlight.findCurrentByIdentifier(identifier)
                .map(FlightWeb::from);
    }

    private Optional<ScheduledFlightWeb> currentScheduledFlightByIdentifier(String identifier) {
        return findScheduledFlight.findCurrentByFlightIdentifier(identifier)
                .map(ScheduledFlightWeb::from);
    }

    private Optional<FlightInformationWeb> createFlightInformation(Optional<FlightWeb> flight,
                                                         Optional<ScheduledFlightWeb> scheduledFlight) {
        if (flight.isPresent() && scheduledFlight.isPresent())
            return Optional.of(FlightInformationWeb.from(flight.get(), scheduledFlight.get()));

        return flight.map(FlightInformationWeb::from)
                .or(() -> scheduledFlight.map(FlightInformationWeb::from));
    }
}
