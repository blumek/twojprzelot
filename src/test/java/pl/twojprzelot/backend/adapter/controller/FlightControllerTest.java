package pl.twojprzelot.backend.adapter.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.usecase.FindFlight;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

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
    void findAllTest_FlightExists() {
        Flight firstFlight = Flight.builder()
                .id(ID)
                .build();

        Flight secondFlight = Flight.builder()
                .id(ANOTHER_ID)
                .build();

        when(findFlight.findAll())
                .thenReturn(Lists.newArrayList(firstFlight, secondFlight));

        FlightWeb firstFlightWeb = FlightWeb.builder()
                .id(ID)
                .build();

        FlightWeb secondFlightWeb = FlightWeb.builder()
                .id(ANOTHER_ID)
                .build();

        List<FlightWeb> foundFlights = flightController.findAll();
        assertThat(foundFlights, containsInAnyOrder(firstFlightWeb, secondFlightWeb));

        verify(findFlight).findAll();
    }
}