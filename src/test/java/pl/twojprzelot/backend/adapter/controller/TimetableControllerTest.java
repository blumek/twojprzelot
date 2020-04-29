package pl.twojprzelot.backend.adapter.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.adapter.controller.model.ScheduledFlightWeb;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimetableControllerTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";
    private static final String ID = "ID";

    @InjectMocks
    private TimetableController timetableController;
    @Mock
    private FindScheduledFlight findScheduledFlight;

    @Test
    void findByIdentifierTest_scheduledFlightNotExists() {
        when(findScheduledFlight.findByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.empty());

        Optional<ScheduledFlightWeb> foundScheduledFlight = timetableController.findByFlightIdentifier(FLIGHT_IDENTIFIER);
        assertEquals(Optional.empty(), foundScheduledFlight);
    }

    @Test
    void findByIdentifierTest_scheduledFlightExists() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        when(findScheduledFlight.findByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.of(scheduledFlight));

        ScheduledFlightWeb scheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ID)
                .build();

        Optional<ScheduledFlightWeb> foundScheduledFlight = timetableController.findByFlightIdentifier(FLIGHT_IDENTIFIER);
        assertEquals(Optional.of(scheduledFlightWeb), foundScheduledFlight);
    }
}