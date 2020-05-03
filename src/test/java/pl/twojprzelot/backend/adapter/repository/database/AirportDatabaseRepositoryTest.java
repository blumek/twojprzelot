package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airport;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";

    @InjectMocks
    private AirportDatabaseRepository airportDatabaseRepository;
    @Mock
    private AirportSpringRepository airportSpringRepository;

    @Test
    void findByIataCodeTest_airportWithGivenIataCodeNotExists() {
        when(airportSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airportDatabaseRepository.findByIataCode(IATA_CODE));
    }

    @Test
    void findByIataCodeTest_airportWithGivenIataCodeExists() {
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setId(ID);
        airportEntity.setIataCode(IATA_CODE);

        when(airportSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(airportEntity));

        Airport airport = Airport.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .build();

        assertEquals(Optional.of(airport), airportDatabaseRepository.findByIataCode(IATA_CODE));
    }

    @Test
    void findByIcaoCode_airportWithGivenIcaoCodeNotExists() {
        when(airportSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airportDatabaseRepository.findByIcaoCode(ICAO_CODE));
    }

    @Test
    void findByIcaoCode_airportWithGivenIcaoCodeExists() {
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setId(ID);
        airportEntity.setIcaoCode(ICAO_CODE);

        when(airportSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.of(airportEntity));

        Airport airport = Airport.builder()
                .id(ID)
                .icaoCode(ICAO_CODE)
                .build();

        assertEquals(Optional.of(airport), airportDatabaseRepository.findByIcaoCode(ICAO_CODE));
    }
}