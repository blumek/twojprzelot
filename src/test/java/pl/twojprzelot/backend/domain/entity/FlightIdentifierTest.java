package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightIdentifierTest {
    private static final int NUMBER = 1;
    private static final int ANOTHER_NUMBER = 2;
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";

    private FlightIdentifier firstFlightIdentifier;
    private FlightIdentifier sameFlightIdentifierAsFirstFlightIdentifier;
    private FlightIdentifier anotherFlightIdentifier;

    @BeforeEach
    void setUp() {
        firstFlightIdentifier = FlightIdentifier.builder()
                .number(NUMBER)
                .iataNumber(IATA_NUMBER)
                .icaoNumber(ICAO_NUMBER)
                .build();

        sameFlightIdentifierAsFirstFlightIdentifier = FlightIdentifier.builder()
                .number(NUMBER)
                .iataNumber(IATA_NUMBER)
                .icaoNumber(ICAO_NUMBER)
                .build();

        anotherFlightIdentifier = FlightIdentifier.builder()
                .number(ANOTHER_NUMBER)
                .iataNumber(IATA_NUMBER)
                .icaoNumber(ICAO_NUMBER)
                .build();
    }

    @Test
    void builderTest_number() {
        FlightIdentifier flightIdentifier = FlightIdentifier.builder()
                .number(NUMBER)
                .build();

        assertEquals(NUMBER, flightIdentifier.getNumber());
    }

    @Test
    void builderTest_iataNumber() {
        FlightIdentifier flightIdentifier = FlightIdentifier.builder()
                .iataNumber(IATA_NUMBER)
                .build();

        assertEquals(IATA_NUMBER, flightIdentifier.getIataNumber());
    }

    @Test
    void builderTest_icaoNumber() {
        FlightIdentifier flightIdentifier = FlightIdentifier.builder()
                .icaoNumber(ICAO_NUMBER)
                .build();

        assertEquals(ICAO_NUMBER, flightIdentifier.getIcaoNumber());
    }

    @Test
    void builderTest_toBuilder() {
        FlightIdentifier flightIdentifier = FlightIdentifier.builder()
                .number(NUMBER)
                .iataNumber(IATA_NUMBER)
                .icaoNumber(ICAO_NUMBER)
                .build();

        FlightIdentifier modifiedFlightIdentifier = flightIdentifier.toBuilder()
                .number(ANOTHER_NUMBER)
                .build();

        FlightIdentifier expectedFlightIdentifier = FlightIdentifier.builder()
                .number(ANOTHER_NUMBER)
                .iataNumber(IATA_NUMBER)
                .icaoNumber(ICAO_NUMBER)
                .build();

        assertEquals(expectedFlightIdentifier, modifiedFlightIdentifier);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(FlightIdentifier.class).verify();
    }
}