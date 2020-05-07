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
    private Airport expectedAirport;
    private Airport anotherExpectedAirport;

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

        expectedAirport = Airport.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        anotherExpectedAirport = Airport.builder()
                .id(ANOTHER_ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();
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

        assertEquals(Optional.of(expectedAirport), airportDatabaseRepository.findByIataCode(IATA_CODE));

        verify(airportSpringRepository).findByIataCode(IATA_CODE);
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

        assertEquals(Optional.of(expectedAirport), airportDatabaseRepository.findByIcaoCode(ICAO_CODE));

        verify(airportSpringRepository).findByIcaoCode(ICAO_CODE);
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
        assertThat(foundAirports, containsInAnyOrder(expectedAirport, anotherExpectedAirport));

        verify(airportSpringRepository).findAll();
    }
}