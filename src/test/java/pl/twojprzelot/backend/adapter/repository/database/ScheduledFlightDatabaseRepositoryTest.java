package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    private ScheduledFlight scheduledFlight;
    private ScheduledFlight anotherScheduledFlight;

    @BeforeEach
    void setUp() {
        scheduledFlightEntity = new ScheduledFlightEntity();
        scheduledFlightEntity.setId(ID);

        anotherScheduledFlightEntity = new ScheduledFlightEntity();
        anotherScheduledFlightEntity.setId(ANOTHER_ID);

        scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        anotherScheduledFlight = ScheduledFlight.builder()
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
        assertThat(foundFlights, containsInAnyOrder(scheduledFlight, anotherScheduledFlight));

        verify(scheduledFlightSpringRepository).findAll();
    }

    @Test
    void findAllByIataNumberTest_flightsWithGivenIataNumberNotExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifierIataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertTrue(foundFlights.isEmpty());

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifierIataNumber(IATA_NUMBER);
    }

    @Test
    void findAllByIataNumberTest_flightWithGivenIataNumberExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifierIataNumber(IATA_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlightEntity));

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIataNumber(IATA_NUMBER);
        assertThat(foundFlights, containsInAnyOrder(scheduledFlight));

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifierIataNumber(IATA_NUMBER);
    }

    @Test
    void findAllByIataNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.findAllByIataNumber(null));

        verify(scheduledFlightSpringRepository, never()).findAllByFlightIdentifierIataNumber(null);
    }

    @Test
    void findAllByIcaoNumber_flightsWithGivenIcaoNumberNotExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifierIcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertTrue(foundFlights.isEmpty());

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifierIcaoNumber(ICAO_NUMBER);
    }

    @Test
    void findAllByIcaoNumber_flightWithGivenIcaoNumberExist() {
        when(scheduledFlightSpringRepository.findAllByFlightIdentifierIcaoNumber(ICAO_NUMBER))
                .thenReturn(Lists.newArrayList(scheduledFlightEntity));

        List<ScheduledFlight> foundFlights = scheduledFlightDatabaseRepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertThat(foundFlights, containsInAnyOrder(scheduledFlight));

        verify(scheduledFlightSpringRepository).findAllByFlightIdentifierIcaoNumber(ICAO_NUMBER);
    }

    @Test
    void findAllByIcaoNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.findAllByIcaoNumber(null));

        verify(scheduledFlightSpringRepository, never()).findAllByFlightIdentifierIcaoNumber(null);
    }

    @Test
    void createTest() {
        when(scheduledFlightSpringRepository.save(scheduledFlightEntity))
                .thenReturn(anotherScheduledFlightEntity);

        ScheduledFlight createdScheduledFlight = scheduledFlightDatabaseRepository.create(scheduledFlight);
        assertEquals(anotherScheduledFlight, createdScheduledFlight);

        verify(scheduledFlightSpringRepository).save(scheduledFlightEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.create(null));

        verify(scheduledFlightSpringRepository, never()).save(null);
    }

    @Test
    void overrideAllTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.overrideAll(null));

        verify(scheduledFlightSpringRepository, never()).deleteAll();
        verify(scheduledFlightSpringRepository, never()).flush();
        verify(scheduledFlightSpringRepository, never()).saveAll(any());
    }

    @Test
    void overrideAllTest_noScheduledFlightsToImport() {
        List<ScheduledFlight> createdScheduledFlights = scheduledFlightDatabaseRepository.overrideAll(Lists.newArrayList());
        assertTrue(createdScheduledFlights.isEmpty());

        verify(scheduledFlightSpringRepository).deleteAll();
        verify(scheduledFlightSpringRepository).flush();
        verify(scheduledFlightSpringRepository).saveAll(Lists.newArrayList());
    }

    @Test
    void overrideAllTest_oneScheduledFlightToImport() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .build())
                .build();

        ScheduledFlight createdScheduledFlight = scheduledFlight.toBuilder()
                .id(ID)
                .build();

        FlightIdentifierEmbeddable flightIdentifier = new FlightIdentifierEmbeddable();
        flightIdentifier.setIataNumber(IATA_NUMBER);

        ScheduledFlightEntity scheduledFlightEntity = new ScheduledFlightEntity();
        scheduledFlightEntity.setFlightIdentifier(flightIdentifier);

        ScheduledFlightEntity createdScheduledFlightEntity = new ScheduledFlightEntity();
        createdScheduledFlightEntity.setId(ID);
        createdScheduledFlightEntity.setFlightIdentifier(flightIdentifier);

        List<ScheduledFlightEntity> scheduledFlightEntitiesToCreate = Lists.newArrayList(scheduledFlightEntity);
        List<ScheduledFlightEntity> createdScheduledFlightEntities = Lists.newArrayList(createdScheduledFlightEntity);
        when(scheduledFlightSpringRepository.saveAll(scheduledFlightEntitiesToCreate))
                .thenReturn(createdScheduledFlightEntities);

        List<ScheduledFlight> scheduledFlightsToCreate = Lists.newArrayList(scheduledFlight);
        List<ScheduledFlight> createdScheduledFlights = scheduledFlightDatabaseRepository.overrideAll(scheduledFlightsToCreate);
        assertThat(createdScheduledFlights, containsInAnyOrder(createdScheduledFlight));

        verify(scheduledFlightSpringRepository).deleteAll();
        verify(scheduledFlightSpringRepository).flush();
        verify(scheduledFlightSpringRepository).saveAll(scheduledFlightEntitiesToCreate);
    }

    @Test
    void updateTest_entityWithId() {
        when(scheduledFlightSpringRepository.save(scheduledFlightEntity))
                .thenReturn(anotherScheduledFlightEntity);

        ScheduledFlight updatedScheduledFlight = scheduledFlightDatabaseRepository.update(scheduledFlight);
        assertEquals(anotherScheduledFlight, updatedScheduledFlight);

        verify(scheduledFlightSpringRepository).save(scheduledFlightEntity);
    }

    @Test
    void updateTest_entityWithoutId() {
        removeId();
        assertThrows(IllegalArgumentException.class, () -> scheduledFlightDatabaseRepository.update(scheduledFlight));

        verify(scheduledFlightSpringRepository, never()).save(scheduledFlightEntity);
    }

    private void removeId() {
        scheduledFlight = scheduledFlight.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void updateTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightDatabaseRepository.update(null));

        verify(scheduledFlightSpringRepository, never()).save(null);
    }

    @Test
    void removeAllTest() {
        scheduledFlightDatabaseRepository.removeAll();

        verify(scheduledFlightSpringRepository).deleteAll();
    }
}