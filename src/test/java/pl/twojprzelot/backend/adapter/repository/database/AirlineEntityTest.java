package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.Airline;

import static org.junit.jupiter.api.Assertions.*;

class AirlineEntityTest {
    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final String NAME = "NAME";

    private AirlineEntity airlineEntity;
    private Airline airline;

    @BeforeEach
    void setUp() {
        airlineEntity = new AirlineEntity();
        airlineEntity.setId(ID);
        airlineEntity.setIataCode(IATA_CODE);
        airlineEntity.setIcaoCode(ICAO_CODE);
        airlineEntity.setName(NAME);

        airline = Airline.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .name(NAME)
                .build();
    }

    @Test
    void toAirlineTest() {
        assertEquals(airline, airlineEntity.toAirline());
    }

    @Test
    void fromTest() {
        assertEquals(airlineEntity, AirlineEntity.from(airline));
    }
}