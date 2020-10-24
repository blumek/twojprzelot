package pl.twojprzelot.backend.adapter.controller;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.ERROR;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.SUCCESS;

@RequiredArgsConstructor
@RequestMapping("flights")
@RestController
final class SpringFlightController {
    private final FlightController flightController;

    @GetMapping
    public ResponseEntity<ResponseWeb<List<FlightWeb>>> findAllBy() {
        List<FlightWeb> foundFlights = flightController.findAll();
        if (foundFlights.isEmpty())
            return noResultsResponse();

        return responseWithFlights(foundFlights);
    }

    private ResponseEntity<ResponseWeb<List<FlightWeb>>> noResultsResponse() {
        return ResponseEntity.status(NOT_FOUND)
                .body(ResponseWeb.<List<FlightWeb>>builder()
                        .status(ERROR)
                        .data(Lists.newArrayList())
                        .message("No available flights")
                        .build());
    }

    private ResponseEntity<ResponseWeb<List<FlightWeb>>> responseWithFlights(List<FlightWeb> foundFlights) {
        return ResponseEntity.status(OK)
                .body(ResponseWeb.<List<FlightWeb>>builder()
                        .status(SUCCESS)
                        .data(foundFlights)
                        .build());
    }
}
