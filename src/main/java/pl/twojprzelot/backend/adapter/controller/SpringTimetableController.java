package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
class SpringTimetableController {
    private final TimetableController timetableController;

    @GetMapping("/timetable/{identifier}")
    public List<ScheduledFlightWeb> findAllByFlightIdentifier(@PathVariable String identifier) {
        return timetableController.findAllByFlightIdentifier(identifier);
    }
}