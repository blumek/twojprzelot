package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";

    @InjectMocks
    private AirlineDatabaseRepository airlineDatabaseRepository;
    @Mock
    private AirlineSpringRepository airlineSpringRepository;

    @Test
    void findByIataCodeTest_airlineWithGivenIataCodeNotExists() {
        when(airlineSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airlineDatabaseRepository.findByIataCode(IATA_CODE));
    }

    @Test
    void findByIataCodeTest_airlineWithGivenIataCodeExists() {
        AirlineEntity airlineEntity = new AirlineEntity();
        airlineEntity.setId(ID);
        airlineEntity.setIataCode(IATA_CODE);

        when(airlineSpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(airlineEntity));

        Airline airline = Airline.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .build();

        assertEquals(Optional.of(airline), airlineDatabaseRepository.findByIataCode(IATA_CODE));
    }

    @Test
    void findByIcaoCode_airlineWithGivenIcaoCodeNotExists() {
        when(airlineSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airlineDatabaseRepository.findByIcaoCode(ICAO_CODE));
    }

    @Test
    void findByIcaoCode_airlineWithGivenIcaoCodeExists() {
        AirlineEntity airlineEntity = new AirlineEntity();
        airlineEntity.setId(ID);
        airlineEntity.setIcaoCode(ICAO_CODE);

        when(airlineSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.of(airlineEntity));

        Airline airline = Airline.builder()
                .id(ID)
                .icaoCode(ICAO_CODE)
                .build();

        assertEquals(Optional.of(airline), airlineDatabaseRepository.findByIcaoCode(ICAO_CODE));
    }
}