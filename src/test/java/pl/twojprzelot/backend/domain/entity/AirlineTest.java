package pl.twojprzelot.backend.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineTest {
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String ANOTHER_NAME = "ANOTHER_NAME";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";

    private Airline firstAirline;
    private Airline sameAirlineAsFirstArline;
    private Airline anotherAirline;

    @BeforeEach
    void setUp() {
        firstAirline = Airline.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        sameAirlineAsFirstArline = Airline.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        anotherAirline = Airline.builder()
                .id(ID)
                .name(ANOTHER_NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();
    }

    @Test
    void builderTest_id() {
        Airline airline = Airline.builder()
                .id(ID)
                .build();

        assertEquals(ID, airline.getId());
    }

    @Test
    void builderTest_name() {
        Airline airline = Airline.builder()
                .name(NAME)
                .build();

        assertEquals(NAME, airline.getName());
    }

    @Test
    void builderTest_iataCode() {
        Airline airline = Airline.builder()
                .iataCode(IATA_CODE)
                .build();

        assertEquals(IATA_CODE, airline.getIataCode());
    }

    @Test
    void builderTest_icaoCode() {
        Airline airline = Airline.builder()
                .icaoCode(ICAO_CODE)
                .build();

        assertEquals(ICAO_CODE, airline.getIcaoCode());
    }

    @Test
    void builderTest_toBuilder() {
        Airline airline = Airline.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        Airline modifiedAirline = airline.toBuilder()
                .name(ANOTHER_NAME)
                .build();

        Airline expectedAirline = Airline.builder()
                .id(ID)
                .name(ANOTHER_NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        assertEquals(expectedAirline, modifiedAirline);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstAirline, sameAirlineAsFirstArline);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstAirline, anotherAirline);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstAirline.hashCode(), sameAirlineAsFirstArline.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstAirline.hashCode(), anotherAirline.hashCode());
    }
}