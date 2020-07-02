package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.adapter.repository.AssociateScheduledFlight;
import pl.twojprzelot.backend.domain.entity.FlightEndpointDetails;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssociatedScheduledFlightDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final int DELAY_MINUTES = 10;
    public static final String IATA_NUMBER = "IATA_NUMBER";
    public static final String ICAO_NUMBER = "ICAO_NUMBER";

    @InjectMocks
    private AssociatedScheduledFlightDatabaseRepository associationDatabaseRepository;
    @Mock
    private ScheduledFlightImmutableRepository scheduledFightRepository;
    @Mock
    private AssociateScheduledFlight associateScheduledFlight;

    private ScheduledFlight scheduledFlight;
    private ScheduledFlight associatedScheduledFlight;

    @BeforeEach
    void setUp() {
        scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        associatedScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .departure(FlightEndpointDetails.builder()
                        .delayMinutes(DELAY_MINUTES)
                        .build())
                .build();
    }

    @Test
    void findAll_noFlightsAvailable() {
        when(scheduledFightRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> scheduledFlights = associationDatabaseRepository.findAll();
        assertTrue(scheduledFlights.isEmpty());

        verify(scheduledFightRepository).findAll();
        verify(associateScheduledFlight, never()).getAssociatedScheduledFlight(any());
    }

    @Test
    void findAll_oneFlightAvailable() {
        when(scheduledFightRepository.findAll())
                .thenReturn(Lists.newArrayList(scheduledFlight));

        when(associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight))
                .thenReturn(associatedScheduledFlight);

        List<ScheduledFlight> scheduledFlights = associationDatabaseRepository.findAll();
        assertThat(scheduledFlights, containsInAnyOrder(associatedScheduledFlight));

        verify(scheduledFightRepository).findAll();
        verify(associateScheduledFlight).getAssociatedScheduledFlight(scheduledFlight);
    }

    @Test
    void findAllByIataNumber_noFlightsAvailable() {
        when(scheduledFightRepository.findAllByIataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> scheduledFlights = associationDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertTrue(scheduledFlights.isEmpty());

        verify(scheduledFightRepository).findAllByIataNumber(IATA_NUMBER);
        verify(associateScheduledFlight, never()).getAssociatedScheduledFlight(any());
    }

    @Test
    void findAllByIataNumber_oneFlightAvailable() {
        when(scheduledFightRepository.findAllByIataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlight));

        when(associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight))
                .thenReturn(associatedScheduledFlight);

        List<ScheduledFlight> scheduledFlights = associationDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertThat(scheduledFlights, containsInAnyOrder(associatedScheduledFlight));

        verify(scheduledFightRepository).findAllByIataNumber(IATA_NUMBER);
        verify(associateScheduledFlight).getAssociatedScheduledFlight(scheduledFlight);
    }

    @Test
    void findAllByIcaoNumber_noFlightsAvailable() {
        when(scheduledFightRepository.findAllByIcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> scheduledFlights = associationDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertTrue(scheduledFlights.isEmpty());

        verify(scheduledFightRepository).findAllByIcaoNumber(ICAO_NUMBER);
        verify(associateScheduledFlight, never()).getAssociatedScheduledFlight(any());
    }

    @Test
    void findAllByIcaoNumber_oneFlightAvailable() {
        when(scheduledFightRepository.findAllByIcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlight));

        when(associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight))
                .thenReturn(associatedScheduledFlight);

        List<ScheduledFlight> scheduledFlights = associationDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertThat(scheduledFlights, containsInAnyOrder(associatedScheduledFlight));

        verify(scheduledFightRepository).findAllByIcaoNumber(ICAO_NUMBER);
        verify(associateScheduledFlight).getAssociatedScheduledFlight(scheduledFlight);
    }
}