package pl.twojprzelot.backend.adapter.controller;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.usecase.FindFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
final class FlightInformationController {
    private final FindFlight findFlight;
    private final FindScheduledFlight findScheduledFlight;

    public List<FlightInformationWeb> findAllByFlightIdentifier(String identifier) {
        List<FlightWeb> flights = flightsByIdentifier(identifier);
        List<ScheduledFlightWeb> scheduledFlights = scheduledFlightsByIdentifier(identifier);
        return createFlightInformation(flights, scheduledFlights);
    }

    private List<FlightWeb> flightsByIdentifier(String identifier) {
        return findFlight.findAllByFlightIdentifier(identifier)
                .stream()
                .map(FlightWeb::from)
                .collect(toList());
    }

    private List<ScheduledFlightWeb> scheduledFlightsByIdentifier(String identifier) {
        return findScheduledFlight.findAllByFlightIdentifier(identifier)
                .stream()
                .map(ScheduledFlightWeb::from)
                .collect(toList());
    }

    private List<FlightInformationWeb> createFlightInformation(List<FlightWeb> flights,
                                                               List<ScheduledFlightWeb> scheduledFlights) {
        List<FlightInformationWeb> flightInformation = Lists.newArrayList();
        // TODO Add logic

        return flightInformation;
    }
}
