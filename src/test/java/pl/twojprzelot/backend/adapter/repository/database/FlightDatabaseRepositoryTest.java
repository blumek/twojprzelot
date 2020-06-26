package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

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