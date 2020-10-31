package pl.twojprzelot.backend.adapter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.ERROR;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.SUCCESS;

@RequiredArgsConstructor
@RequestMapping("flight-information")
@RestController
final class SpringFlightInformationController {
    private final FlightInformationController flightInformationController;

    @GetMapping("/{flightIdentifier}")
    public ResponseEntity<ResponseWeb<FlightInformationWeb>> findCurrentByFlightIdentifier(
            @PathVariable String flightIdentifier) {
        Optional<FlightInformationWeb> foundFlightInformation =
                flightInformationController.findCurrentByFlightIdentifier(flightIdentifier);
        if (foundFlightInformation.isEmpty())
            return noResultsResponse();

        return responseWithFlight(foundFlightInformation.get());
    }

    private ResponseEntity<ResponseWeb<FlightInformationWeb>> noResultsResponse() {
        return ResponseEntity.status(NOT_FOUND)
                .body(ResponseWeb.<FlightInformationWeb>builder()
                        .status(ERROR)
                        .message("Flight information with given ID not found")
                        .build());
    }

    private ResponseEntity<ResponseWeb<FlightInformationWeb>> responseWithFlight(
            FlightInformationWeb flightInformation) {
        return ResponseEntity.status(OK)
                .body(ResponseWeb.<FlightInformationWeb>builder()
                        .status(SUCCESS)
                        .data(flightInformation)
                        .build());
    }
}
