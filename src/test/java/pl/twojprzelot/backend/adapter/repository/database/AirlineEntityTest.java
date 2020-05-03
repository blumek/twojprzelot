package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.Airline;

import static org.junit.jupiter.api.Assertions.*;

class AirlineEntityTest {
    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final String NAME = "NAME";

    @Test
    void toAirlineTest() {
        AirlineEntity airlineEntity = new AirlineEntity();
        airlineEntity.setId(ID);
        airlineEntity.setIataCode(IATA_CODE);
        airlineEntity.setIcaoCode(ICAO_CODE);
        airlineEntity.setName(NAME);

        Airline airline = Airline.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .name(NAME)
                .build();

        assertEquals(airline, airlineEntity.toAirline());
    }
}