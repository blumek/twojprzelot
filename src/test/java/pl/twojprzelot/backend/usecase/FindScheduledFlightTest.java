package pl.twojprzelot.backend.usecase;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindScheduledFlightTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private FindScheduledFlight findScheduledFlight;
    @Mock
    private ScheduledFlightImmutableRepository scheduledFlightImmutableRepository;

    private ScheduledFlight firstScheduledFlight;
    private ScheduledFlight secondScheduledFlight;

    @BeforeEach
    void setUp() {
        firstScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        secondScheduledFlight = ScheduledFlight.builder()
                .id(ANOTHER_ID)
                .build();
    }

    @Test
    void findAllByFlightIdentifier_scheduledFlightsWithGivenIdentifierNotExist() {
        when(scheduledFlightImmutableRepository.findAllByIataNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList());

        when(scheduledFlightImmutableRepository.findAllByIcaoNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundScheduledFlights =
                findScheduledFlight.findAllByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertTrue(foundScheduledFlights.isEmpty());

        verify(scheduledFlightImmutableRepository).findAllByIataNumber(FLIGHT_IDENTIFIER);
        verify(scheduledFlightImmutableRepository).findAllByIcaoNumber(FLIGHT_IDENTIFIER);
    }

    @Test
    void findAllByFlightIdentifier_scheduledFlightsWithGivenIataNumberExist() {
        when(scheduledFlightImmutableRepository.findAllByIataNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList(firstScheduledFlight, secondScheduledFlight));

        List<ScheduledFlight> foundScheduledFlights =
                findScheduledFlight.findAllByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertThat(foundScheduledFlights, containsInAnyOrder(firstScheduledFlight, secondScheduledFlight));

        verify(scheduledFlightImmutableRepository).findAllByIataNumber(FLIGHT_IDENTIFIER);
    }

    @Test
    void findAllByFlightIdentifier_scheduledFlightWithGivenIcaoNumberExists() {
        when(scheduledFlightImmutableRepository.findAllByIataNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList());

        when(scheduledFlightImmutableRepository.findAllByIcaoNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList(firstScheduledFlight, secondScheduledFlight));

        List<ScheduledFlight> foundScheduledFlights =
                findScheduledFlight.findAllByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertThat(foundScheduledFlights, containsInAnyOrder(firstScheduledFlight, secondScheduledFlight));

        verify(scheduledFlightImmutableRepository).findAllByIcaoNumber(FLIGHT_IDENTIFIER);
    }
}