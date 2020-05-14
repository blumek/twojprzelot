package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airport;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";

    @InjectMocks
    private AirportDatabaseRepository airportDatabaseRepository;
    @Mock
    private AirportSpringRepository airportSpringRepository;

    private AirportEntity airportEntity;
    private AirportEntity anotherAirportEntity;
    private Airport airport;
    private Airport anotherAirport;

    @BeforeEach
    void setUp() {
        airportEntity = new AirportEntity();
        airportEntity.setId(ID);
        airportEntity.setIataCode(IATA_CODE);
        airportEntity.setIcaoCode(ICAO_CODE);

        anotherAirportEntity = new AirportEntity();
        anotherAirportEntity.setId(ANOTHER_ID);
        anotherAirportEntity.setIataCode(IATA_CODE);
        anotherAirportEntity.setIcaoCode(ICAO_CODE);

        airport = Airport.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        anotherAirport = Airport.builder()
                .id(ANOTHER_ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();
    }

    @Test
    void findAllTest_noAirportsAvailable() {
        when(airportSpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<Airport> foundAirports = airportDatabaseRepository.findAll();
        assertTrue(foundAirports.isEmpty());

        verify(airportSpringRepository).findAll();
    }

    @Test
    void findAllTest_twoAirportsAvailable() {
        when(airportSpringRepository.findAll())
                .thenReturn(Lists.newArrayList(airportEntity, anotherAirportEntity));

        List<Airport> foundAirports = airportDatabaseRepository.findAll();
        assertThat(foundAirports, containsInAnyOrder(airport, anotherAirport));

        verify(airportSpringRepository).findAll();
    }

    @Test
    void findByIataCodeTest_airportWithGivenIataCodeNotExists() {
        when(airportSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airportDatabaseRepository.findByIataCode(IATA_CODE));

        verify(airportSpringRepository).findByIataCode(IATA_CODE);
    }

    @Test
    void findByIataCodeTest_airportWithGivenIataCodeExists() {
        when(airportSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(airportEntity));

        assertEquals(Optional.of(airport), airportDatabaseRepository.findByIataCode(IATA_CODE));

        verify(airportSpringRepository).findByIataCode(IATA_CODE);
    }

    @Test
    void findByIataCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airportDatabaseRepository.findByIataCode(null));

        verify(airportSpringRepository, never()).findByIataCode(null);
    }

    @Test
    void findByIcaoCode_airportWithGivenIcaoCodeNotExists() {
        when(airportSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airportDatabaseRepository.findByIcaoCode(ICAO_CODE));

        verify(airportSpringRepository).findByIcaoCode(ICAO_CODE);
    }

    @Test
    void findByIcaoCode_airportWithGivenIcaoCodeExists() {
        when(airportSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.of(airportEntity));

        assertEquals(Optional.of(airport), airportDatabaseRepository.findByIcaoCode(ICAO_CODE));

        verify(airportSpringRepository).findByIcaoCode(ICAO_CODE);
    }

    @Test
    void findByIcaoCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airportDatabaseRepository.findByIcaoCode(null));

        verify(airportSpringRepository, never()).findByIcaoCode(null);
    }

    @Test
    void createTest() {
        when(airportSpringRepository.save(airportEntity))
                .thenReturn(anotherAirportEntity);

        Airport createdAirport = airportDatabaseRepository.create(airport);
        assertEquals(anotherAirport, createdAirport);

        verify(airportSpringRepository).save(airportEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airportDatabaseRepository.create(null));

        verify(airportSpringRepository, never()).save(null);
    }

    @Test
    void updateTest_entityWithId() {
        when(airportSpringRepository.save(airportEntity))
                .thenReturn(anotherAirportEntity);

        Airport updatedAirport = airportDatabaseRepository.update(airport);
        assertEquals(anotherAirport, updatedAirport);

        verify(airportSpringRepository).save(airportEntity);
    }

    @Test
    void updateTest_entityWithoutId() {
        removeId();

        assertThrows(IllegalArgumentException.class, () -> airportDatabaseRepository.update(airport));

        verify(airportSpringRepository, never()).save(airportEntity);
    }

    private void removeId() {
        airport = airport.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void updateTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airportDatabaseRepository.update(null));

        verify(airportSpringRepository, never()).save(null);
    }
}