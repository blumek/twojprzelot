package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";

    @InjectMocks
    private AirlineDatabaseRepository airlineDatabaseRepository;
    @Mock
    private AirlineSpringRepository airlineSpringRepository;

    private AirlineEntity airlineEntity;
    private AirlineEntity anotherAirlineEntity;
    private Airline expectedAirline;
    private Airline anotherExpectedAirline;

    @BeforeEach
    void setUp() {
        airlineEntity = new AirlineEntity();
        airlineEntity.setId(ID);
        airlineEntity.setIataCode(IATA_CODE);
        airlineEntity.setIcaoCode(ICAO_CODE);

        anotherAirlineEntity = new AirlineEntity();
        anotherAirlineEntity.setId(ANOTHER_ID);
        anotherAirlineEntity.setIataCode(IATA_CODE);
        anotherAirlineEntity.setIcaoCode(ICAO_CODE);

        expectedAirline = Airline.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        anotherExpectedAirline = Airline.builder()
                .id(ANOTHER_ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();
    }

    @Test
    void findByIataCodeTest_airlineWithGivenIataCodeNotExists() {
        when(airlineSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airlineDatabaseRepository.findByIataCode(IATA_CODE));

        verify(airlineSpringRepository).findByIataCode(IATA_CODE);
    }

    @Test
    void findByIataCodeTest_airlineWithGivenIataCodeExists() {
        when(airlineSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(airlineEntity));

        assertEquals(Optional.of(expectedAirline), airlineDatabaseRepository.findByIataCode(IATA_CODE));

        verify(airlineSpringRepository).findByIataCode(IATA_CODE);
    }

    @Test
    void findByIcaoCodeTest_airlineWithGivenIcaoCodeNotExists() {
        when(airlineSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airlineDatabaseRepository.findByIcaoCode(ICAO_CODE));

        verify(airlineSpringRepository).findByIcaoCode(ICAO_CODE);
    }

    @Test
    void findByIcaoCodeTest_airlineWithGivenIcaoCodeExists() {
        when(airlineSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.of(airlineEntity));

        assertEquals(Optional.of(expectedAirline), airlineDatabaseRepository.findByIcaoCode(ICAO_CODE));

        verify(airlineSpringRepository).findByIcaoCode(ICAO_CODE);
    }

    @Test
    void findAllTest_noAirlineAvailable() {
        when(airlineSpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<Airline> foundAirline = airlineDatabaseRepository.findAll();
        assertTrue(foundAirline.isEmpty());

        verify(airlineSpringRepository).findAll();
    }

    @Test
    void findAllTest_twoAirlineAvailable() {
        when(airlineSpringRepository.findAll())
                .thenReturn(Lists.newArrayList(airlineEntity, anotherAirlineEntity));

        List<Airline> foundAirline = airlineDatabaseRepository.findAll();
        assertThat(foundAirline, containsInAnyOrder(expectedAirline, anotherExpectedAirline));

        verify(airlineSpringRepository).findAll();
    }
}