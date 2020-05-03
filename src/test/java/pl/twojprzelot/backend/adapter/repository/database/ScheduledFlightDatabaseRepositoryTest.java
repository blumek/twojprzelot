package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ExtendWith(MockitoExtension.class)
class ScheduledFlightDatabaseRepositoryTest {
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";
    private static final int ID = 1;

    @InjectMocks
    private ScheduledFlightDatabaseRepository scheduledFlightDatabaseRepository;
    @Mock
    private ScheduledFlightSpringRepository scheduledFlightSpringRepository;

    private ScheduledFlightEntity scheduledFlightEntity;
    private ScheduledFlight scheduledFlight;

    @BeforeEach
    void setUp() {
        scheduledFlightEntity = new ScheduledFlightEntity();
        scheduledFlightEntity.setId(ID);

        scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();
    }

    @Test
    void findAllByIataNumberTest_flightsWithGivenIataNumberNotExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertTrue(foundFlights.isEmpty());
    }

    @Test
    void findAllByIataNumberTest_flightWithGivenIataNumberExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlightEntity));

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertThat(foundFlights, containsInAnyOrder(scheduledFlight));
    }

    @Test
    void findAllByIcaoNumber_flightsWithGivenIcaoNumberNotExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertTrue(foundFlights.isEmpty());
    }

    @Test
    void findAllByIcaoNumber_flightWithGivenIcaoNumberExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlightEntity));

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertThat(foundFlights, containsInAnyOrder(scheduledFlight));
    }
}