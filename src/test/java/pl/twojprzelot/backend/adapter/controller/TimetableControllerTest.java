package pl.twojprzelot.backend.adapter.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.adapter.controller.model.ScheduledFlightWeb;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimetableControllerTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private TimetableController timetableController;
    @Mock
    private FindScheduledFlight findScheduledFlight;

    @Test
    void findAllByIdentifierTest_scheduledFlightsWithGivenIdentifierNotExist() {
        when(findScheduledFlight.findAllByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlightWeb> foundScheduledFlights =
                timetableController.findAllByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertTrue(foundScheduledFlights.isEmpty());
    }

    @Test
    void findAllByIdentifierTest_scheduledFlightExists() {
        ScheduledFlight firstScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        ScheduledFlight secondScheduledFlight = ScheduledFlight.builder()
                .id(ANOTHER_ID)
                .build();

        when(findScheduledFlight.findAllByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList(firstScheduledFlight, secondScheduledFlight));

        ScheduledFlightWeb firstScheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ID)
                .build();

        ScheduledFlightWeb secondScheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ANOTHER_ID)
                .build();

        List<ScheduledFlightWeb> foundScheduledFlights =
                timetableController.findAllByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertThat(foundScheduledFlights, containsInAnyOrder(firstScheduledFlightWeb, secondScheduledFlightWeb));
    }
}