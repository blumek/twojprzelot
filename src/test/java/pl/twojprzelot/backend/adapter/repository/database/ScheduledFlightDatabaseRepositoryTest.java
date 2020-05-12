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
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private ScheduledFlightDatabaseRepository scheduledFlightDatabaseRepository;
    @Mock
    private ScheduledFlightSpringRepository scheduledFlightSpringRepository;

    private ScheduledFlightEntity scheduledFlightEntity;
    private ScheduledFlightEntity anotherScheduledFlightEntity;
    private ScheduledFlight expectedScheduledFlight;
    private ScheduledFlight anotherExpectedScheduledFlight;

    @BeforeEach
    void setUp() {
        scheduledFlightEntity = new ScheduledFlightEntity();
        scheduledFlightEntity.setId(ID);

        anotherScheduledFlightEntity = new ScheduledFlightEntity();
        anotherScheduledFlightEntity.setId(ANOTHER_ID);

        expectedScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        anotherExpectedScheduledFlight = ScheduledFlight.builder()
                .id(ANOTHER_ID)
                .build();
    }

    @Test
    void findAllTest_noScheduledFlightsAvailable() {
        when(scheduledFlightSpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAll();
        assertTrue(foundFlights.isEmpty());

        verify(scheduledFlightSpringRepository).findAll();
    }

    @Test
    void findAllTest_twoScheduledFlightsAvailable() {
        when(scheduledFlightSpringRepository.findAll())
                .thenReturn(Lists.newArrayList(scheduledFlightEntity, anotherScheduledFlightEntity));

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAll();
        assertThat(foundFlights, containsInAnyOrder(expectedScheduledFlight, anotherExpectedScheduledFlight));

        verify(scheduledFlightSpringRepository).findAll();
    }

    @Test
    void findAllByIataNumberTest_flightsWithGivenIataNumberNotExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertTrue(foundFlights.isEmpty());

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifier_IataNumber(IATA_NUMBER);
    }

    @Test
    void findAllByIataNumberTest_flightWithGivenIataNumberExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlightEntity));

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertThat(foundFlights, containsInAnyOrder(expectedScheduledFlight));

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifier_IataNumber(IATA_NUMBER);
    }

    @Test
    void findAllByIataNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.findAllByIataNumber(null));

        verify(scheduledFlightSpringRepository, never()).findAllByFlightIdentifier_IataNumber(null);
    }

    @Test
    void findAllByIcaoNumber_flightsWithGivenIcaoNumberNotExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertTrue(foundFlights.isEmpty());

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifier_IcaoNumber(ICAO_NUMBER);
    }

    @Test
    void findAllByIcaoNumber_flightWithGivenIcaoNumberExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifier_IcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlightEntity));

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertThat(foundFlights, containsInAnyOrder(expectedScheduledFlight));

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifier_IcaoNumber(ICAO_NUMBER);
    }

    @Test
    void findAllByIcaoNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.findAllByIcaoNumber(null));

        verify(scheduledFlightSpringRepository, never()).findAllByFlightIdentifier_IcaoNumber(null);
    }

    @Test
    void createTest() {
        when(scheduledFlightSpringRepository.save(scheduledFlightEntity))
                .thenReturn(anotherScheduledFlightEntity);

        ScheduledFlight createdScheduledFlight = scheduledFlightDatabaseRepository.create(expectedScheduledFlight);
        assertEquals(anotherExpectedScheduledFlight, createdScheduledFlight);

        verify(scheduledFlightSpringRepository).save(scheduledFlightEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.create(null));

        verify(scheduledFlightSpringRepository, never()).save(null);
    }
}