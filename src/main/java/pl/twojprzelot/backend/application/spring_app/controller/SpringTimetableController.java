package pl.twojprzelot.backend.application.spring_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.twojprzelot.backend.adapter.controller.TimetableController;
import pl.twojprzelot.backend.adapter.controller.model.ScheduledFlightWeb;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
class SpringTimetableController {
    private final TimetableController timetableController;

    @GetMapping("/timetable/{identifier}")
    public ScheduledFlightWeb findByFlightIdentifier(@PathVariable String identifier) {
        return timetableController.findByFlightIdentifier(identifier)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        format("Scheduled flight with given identifier not found, identifier = %s", identifier)));
    }
}
