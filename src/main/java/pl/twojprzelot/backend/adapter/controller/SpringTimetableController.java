package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.SUCCESS;

@RequiredArgsConstructor
@RestController
final class SpringTimetableController {
    private final TimetableController timetableController;

    @GetMapping("/timetable/{identifier}")
    public ResponseWeb<List<ScheduledFlightWeb>> findAllByFlightIdentifier(@PathVariable String identifier) {
        return ResponseWeb.<List<ScheduledFlightWeb>>builder()
                .status(SUCCESS)
                .data(timetableController.findAllByFlightIdentifier(identifier))
                .build();
    }
}
