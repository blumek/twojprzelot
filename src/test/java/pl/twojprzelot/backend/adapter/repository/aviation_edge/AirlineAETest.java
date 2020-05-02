package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.Airline;

import static org.junit.jupiter.api.Assertions.*;

class AirlineAETest {
    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final String NAME = "NAME";

    @Test
    void toAirlineTest() {
        AirlineAE airlineAE = new AirlineAE();
        airlineAE.setId(ID);
        airlineAE.setIataCode(IATA_CODE);
        airlineAE.setIcaoCode(ICAO_CODE);
        airlineAE.setName(NAME);

        Airline airline = Airline.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .name(NAME)
                .build();

        assertEquals(airline, airlineAE.toAirline());
    }
}