package pl.twojprzelot.backend.adapter.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;
import pl.twojprzelot.backend.usecase.FindFlight;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {
    private static final String NUMBER = "NUMBER";
    private static final String ANOTHER_NUMBER = "ANOTHER_NUMBER";

    @InjectMocks
    private FlightController flightController;
    @Mock
    private FindFlight findFlight;

    @Test
    void findAllTest_FlightsWithGivenIdentifierNotExist() {
        when(findFlight.findAll())
                .thenReturn(Lists.newArrayList());

        List<FlightWeb> foundFlights = flightController.findAll();
        assertTrue(foundFlights.isEmpty());

        verify(findFlight).findAll();
    }

    @Test
    void findAllTest_twoFlightsExist() {
        Flight firstFlight = Flight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .number(NUMBER)
                        .build())
                .build();

        Flight secondFlight = Flight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .number(ANOTHER_NUMBER)
                        .build())
                .build();

        when(findFlight.findAll())
                .thenReturn(Lists.newArrayList(firstFlight, secondFlight));

        FlightWeb firstFlightWeb = FlightWeb.builder()
                .flightIdentifier(FlightIdentifierWeb.builder()
                        .number(NUMBER)
                        .build())
                .build();

        FlightWeb secondFlightWeb = FlightWeb.builder()
                .flightIdentifier(FlightIdentifierWeb.builder()
                        .number(ANOTHER_NUMBER)
                        .build())
                .build();

        List<FlightWeb> foundFlights = flightController.findAll();
        assertThat(foundFlights, containsInAnyOrder(firstFlightWeb, secondFlightWeb));

        verify(findFlight).findAll();
    }
}