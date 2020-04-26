package pl.twojprzelot.backend.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindScheduledFlightTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";
    private static final String ID = "ID";

    @InjectMocks
    private FindScheduledFlight findScheduledFlight;
    @Mock
    private ScheduledFlightRepository scheduledFlightRepository;

    private ScheduledFlight scheduledFlight;

    @BeforeEach
    void setUp() {
        scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();
    }

    @Test
    void findByFlightIdentifier_scheduledFlightNotExists() {
        when(scheduledFlightRepository.findByIataNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.empty());

        when(scheduledFlightRepository.findByIcaoNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.empty());

        Optional<ScheduledFlight> foundScheduledFlight =
                findScheduledFlight.findByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(Optional.empty(), foundScheduledFlight);

        verify(scheduledFlightRepository).findByIataNumber(FLIGHT_IDENTIFIER);
        verify(scheduledFlightRepository).findByIcaoNumber(FLIGHT_IDENTIFIER);
    }

    @Test
    void findByFlightIdentifier_scheduledFlightWithGivenIataNumberExists() {
        when(scheduledFlightRepository.findByIataNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.of(scheduledFlight));

        Optional<ScheduledFlight> foundScheduledFlight =
                findScheduledFlight.findByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(Optional.of(scheduledFlight), foundScheduledFlight);

        verify(scheduledFlightRepository).findByIataNumber(FLIGHT_IDENTIFIER);
    }

    @Test
    void findByFlightIdentifier_scheduledFlightWithGivenIcaoNumberExists() {
        when(scheduledFlightRepository.findByIataNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.empty());

        when(scheduledFlightRepository.findByIcaoNumber(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.of(scheduledFlight));

        Optional<ScheduledFlight> foundScheduledFlight =
                findScheduledFlight.findByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(Optional.of(scheduledFlight), foundScheduledFlight);

        verify(scheduledFlightRepository).findByIcaoNumber(FLIGHT_IDENTIFIER);
    }
}