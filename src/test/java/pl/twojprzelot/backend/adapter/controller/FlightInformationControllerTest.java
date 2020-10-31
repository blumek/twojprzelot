package pl.twojprzelot.backend.adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.usecase.FindFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FlightInformationControllerTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";

    @InjectMocks
    private FlightInformationController flightInformationController;

    @Mock
    private FindFlight findFlight;

    @Mock
    private FindScheduledFlight findScheduledFlight;

    private Flight flight;
    private FlightWeb flightWeb;
    private ScheduledFlight scheduledFlight;
    private ScheduledFlightWeb scheduledFlightWeb;

    @BeforeEach
    void setUp() {
        flight = Flight.builder()
                .id(1)
                .build();
        flightWeb = FlightWeb.from(flight);

        scheduledFlight = ScheduledFlight.builder()
                .id(2)
                .build();
        scheduledFlightWeb = ScheduledFlightWeb.from(scheduledFlight);
    }

    @Test
    void findCurrentByFlightIdentifier_flightAndScheduledFLightAvailable() {
        when(findFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.of(flight));

        when(findScheduledFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.of(scheduledFlight));

        Optional<FlightInformationWeb> expectedFlightInformation = Optional.of(
                FlightInformationWeb.builder()
                        .flight(flightWeb)
                        .scheduledFlight(scheduledFlightWeb)
                        .build()
        );

        Optional<FlightInformationWeb> foundFlightInformation =
                flightInformationController.findCurrentByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(expectedFlightInformation, foundFlightInformation);
    }

    @Test
    void findCurrentByFlightIdentifier_noFlightAvailable() {
        when(findFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.empty());

        when(findScheduledFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.of(scheduledFlight));

        Optional<FlightInformationWeb> expectedFlightInformation = Optional.of(
                FlightInformationWeb.builder()
                        .scheduledFlight(scheduledFlightWeb)
                        .build()
        );

        Optional<FlightInformationWeb> foundFlightInformation =
                flightInformationController.findCurrentByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(expectedFlightInformation, foundFlightInformation);
    }

    @Test
    void findCurrentByFlightIdentifier_noScheduledFlightAvailable() {
        when(findFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.of(flight));

        when(findScheduledFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.empty());

        Optional<FlightInformationWeb> expectedFlightInformation = Optional.of(
                FlightInformationWeb.builder()
                        .flight(flightWeb)
                        .build()
        );

        Optional<FlightInformationWeb> foundFlightInformation =
                flightInformationController.findCurrentByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(expectedFlightInformation, foundFlightInformation);
    }

    @Test
    void findCurrentByFlightIdentifier_noFlightAndScheduledFLightAvailable() {
        when(findFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.empty());

        when(findScheduledFlight.findCurrentByFlightIdentifier(any()))
                .thenReturn(Optional.empty());

        Optional<FlightInformationWeb> foundFlightInformation =
                flightInformationController.findCurrentByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(Optional.empty(), foundFlightInformation);
    }
}