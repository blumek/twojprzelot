package pl.twojprzelot.backend.adapter.controller;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.ERROR;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.SUCCESS;

@RequiredArgsConstructor
@RequestMapping("flight-information")
@RestController
final class SpringFlightInformationController {
    private final FlightInformationController flightInformationController;

    @GetMapping("/{identifier}")
    public ResponseEntity<ResponseWeb<List<FlightInformationWeb>>> findAllByIdentifier(@PathVariable String identifier) {
        List<FlightInformationWeb> foundFlightInformation = flightInformationController.findAllByFlightIdentifier(identifier);
        if (foundFlightInformation.isEmpty())
            return noResultsResponse();

        return responseWithFlights(foundFlightInformation);
    }

    private ResponseEntity<ResponseWeb<List<FlightInformationWeb>>> noResultsResponse() {
        return ResponseEntity.status(NOT_FOUND)
                .body(ResponseWeb.<List<FlightInformationWeb>>builder()
                        .status(ERROR)
                        .data(Lists.newArrayList())
                        .message("Flight information with given ID not found")
                        .build());
    }

    private ResponseEntity<ResponseWeb<List<FlightInformationWeb>>> responseWithFlights(
            List<FlightInformationWeb> flightInformation) {
        return ResponseEntity.status(OK)
                .body(ResponseWeb.<List<FlightInformationWeb>>builder()
                        .status(SUCCESS)
                        .data(flightInformation)
                        .build());
    }
}
