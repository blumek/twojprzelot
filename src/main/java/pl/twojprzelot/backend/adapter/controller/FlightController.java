package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.usecase.FindFlight;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
final class FlightController {
    private final FindFlight findFlight;

    public List<FlightWeb> findAll() {
        return findFlight.findAll()
                .stream()
                .map(FlightWeb::from)
                .collect(toList());
    }
}
