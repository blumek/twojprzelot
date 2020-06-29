package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String IATA_NUMBER = "IATA_NUMBER";

    @InjectMocks
    private FlightDatabaseRepository flightDatabaseRepository;
    @Mock
    private FlightSpringRepository flightSpringRepository;

    private FlightEntity flightEntity;
    private FlightEntity anotherFlightEntity;
    private Flight flight;
    private Flight anotherFlight;

    @BeforeEach
    void setUp() {
        flightEntity = new FlightEntity();
        flightEntity.setId(ID);

        anotherFlightEntity = new FlightEntity();
        anotherFlightEntity.setId(ANOTHER_ID);

        flight = Flight.builder()
                .id(ID)
                .build();

        anotherFlight = Flight.builder()
                .id(ANOTHER_ID)
                .build();
    }

    @Test
    void findAllTest_noFlightsAvailable() {
        when(flightSpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<Flight> foundFlights = flightDatabaseRepository.findAll();
        assertTrue(foundFlights.isEmpty());

        verify(flightSpringRepository).findAll();
    }

    @Test
    void findAllTest_twoFlightsAvailable() {
        when(flightSpringRepository.findAll())
                .thenReturn(Lists.newArrayList(flightEntity, anotherFlightEntity));

        List<Flight> foundFlights = flightDatabaseRepository.findAll();
        assertThat(foundFlights, containsInAnyOrder(flight, anotherFlight));

        verify(flightSpringRepository).findAll();
    }

    @Test
    void createTest() {
        when(flightSpringRepository.save(flightEntity))
                .thenReturn(anotherFlightEntity);

        Flight createdFlight = flightDatabaseRepository.create(flight);
        assertEquals(anotherFlight, createdFlight);

        verify(flightSpringRepository).save(flightEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> flightDatabaseRepository.create(null));

        verify(flightSpringRepository, never()).save(null);
    }

    @Test
    void overrideAllTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> flightDatabaseRepository.overrideAll(null));

        verify(flightSpringRepository, never()).deleteAll();
        verify(flightSpringRepository, never()).flush();
        verify(flightSpringRepository, never()).saveAll(any());
    }

    @Test
    void overrideAllTest_noFlightsToImport() {
        List<Flight> createdFlights = flightDatabaseRepository.overrideAll(Lists.newArrayList());
        assertTrue(createdFlights.isEmpty());

        verify(flightSpringRepository).deleteAll();
        verify(flightSpringRepository).flush();
        verify(flightSpringRepository).saveAll(Lists.newArrayList());
    }

    @Test
    void overrideAllTest_oneFlightToImport() {
        Flight flight = Flight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .build())
                .build();

        Flight createdFlight = flight.toBuilder()
                .id(ID)
                .build();

        FlightIdentifierEmbeddable flightIdentifier = new FlightIdentifierEmbeddable();
        flightIdentifier.setIataNumber(IATA_NUMBER);

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightIdentifier(flightIdentifier);

        FlightEntity createdFlightEntity = new FlightEntity();
        createdFlightEntity.setId(ID);
        createdFlightEntity.setFlightIdentifier(flightIdentifier);

        List<FlightEntity> flightEntitiesToCreate = Lists.newArrayList(flightEntity);
        List<FlightEntity> createdFlightEntities = Lists.newArrayList(createdFlightEntity);
        when(flightSpringRepository.saveAll(flightEntitiesToCreate))
                .thenReturn(createdFlightEntities);

        List<Flight> flightsToCreate = Lists.newArrayList(flight);
        List<Flight> createdFlights = flightDatabaseRepository.overrideAll(flightsToCreate);
        assertThat(createdFlights, containsInAnyOrder(createdFlight));

        verify(flightSpringRepository).deleteAll();
        verify(flightSpringRepository).flush();
        verify(flightSpringRepository).saveAll(flightEntitiesToCreate);
    }

    @Test
    void updateTest_entityWithId() {
        when(flightSpringRepository.save(flightEntity))
                .thenReturn(anotherFlightEntity);

        Flight updatedFlight = flightDatabaseRepository.update(flight);
        assertEquals(anotherFlight, updatedFlight);

        verify(flightSpringRepository).save(flightEntity);
    }

    @Test
    void updateTest_entityWithoutId() {
        removeId();
        assertThrows(IllegalArgumentException.class, () -> flightDatabaseRepository.update(flight));

        verify(flightSpringRepository, never()).save(flightEntity);
    }

    private void removeId() {
        flight = flight.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void updateTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> flightDatabaseRepository.update(null));

        verify(flightSpringRepository, never()).save(null);
    }

    @Test
    void removeAllTest() {
        flightDatabaseRepository.removeAll();

        verify(flightSpringRepository).deleteAll();
    }
}