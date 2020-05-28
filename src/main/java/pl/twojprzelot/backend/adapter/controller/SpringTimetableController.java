package pl.twojprzelot.backend.adapter.controller;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.*;

@RequiredArgsConstructor
@RestController
final class SpringTimetableController {
    private final TimetableController timetableController;

    @GetMapping("/timetable/{identifier}")
    public ResponseEntity<ResponseWeb<List<ScheduledFlightWeb>>> findAllByFlightIdentifier(@PathVariable String identifier) {
        List<ScheduledFlightWeb> foundFlights = timetableController.findAllByFlightIdentifier(identifier);
        if (foundFlights.isEmpty())
            return noResultsResponse();

        return responseWithFlights(foundFlights);
    }

    private ResponseEntity<ResponseWeb<List<ScheduledFlightWeb>>> noResultsResponse() {
        return ResponseEntity.status(NOT_FOUND)
                .body(ResponseWeb.<List<ScheduledFlightWeb>>builder()
                        .status(ERROR)
                        .data(Lists.newArrayList())
                        .message("Flight with given ID not found")
                        .build());
    }

    private ResponseEntity<ResponseWeb<List<ScheduledFlightWeb>>> responseWithFlights(List<ScheduledFlightWeb> foundFlights) {
        return ResponseEntity.status(OK)
                .body(ResponseWeb.<List<ScheduledFlightWeb>>builder()
                        .status(SUCCESS)
                        .data(foundFlights)
                        .build());
    }
}
