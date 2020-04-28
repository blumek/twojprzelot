package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightIdentifierEmbeddableTest {
    private static final int NUMBER = 600;
    private static final int ANOTHER_NUMBER = 700;
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";

    private FlightIdentifierEmbeddable firstFlightIdentifierEmbeddable;
    private FlightIdentifierEmbeddable sameFlightIdentifierEmbeddableAsFirstFlightIdentifierEmbeddable;
    private FlightIdentifierEmbeddable anotherFlightIdentifierEmbeddable;

    @BeforeEach
    void setUp() {
        firstFlightIdentifierEmbeddable = new FlightIdentifierEmbeddable();
        firstFlightIdentifierEmbeddable.setNumber(NUMBER);
        firstFlightIdentifierEmbeddable.setIataNumber(IATA_NUMBER);
        firstFlightIdentifierEmbeddable.setIcaoNumber(ICAO_NUMBER);

        sameFlightIdentifierEmbeddableAsFirstFlightIdentifierEmbeddable = new FlightIdentifierEmbeddable();
        sameFlightIdentifierEmbeddableAsFirstFlightIdentifierEmbeddable.setNumber(NUMBER);
        sameFlightIdentifierEmbeddableAsFirstFlightIdentifierEmbeddable.setIataNumber(IATA_NUMBER);
        sameFlightIdentifierEmbeddableAsFirstFlightIdentifierEmbeddable.setIcaoNumber(ICAO_NUMBER);

        anotherFlightIdentifierEmbeddable = new FlightIdentifierEmbeddable();
        anotherFlightIdentifierEmbeddable.setNumber(ANOTHER_NUMBER);
        anotherFlightIdentifierEmbeddable.setIataNumber(IATA_NUMBER);
        anotherFlightIdentifierEmbeddable.setIcaoNumber(ICAO_NUMBER);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstFlightIdentifierEmbeddable,
                sameFlightIdentifierEmbeddableAsFirstFlightIdentifierEmbeddable);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstFlightIdentifierEmbeddable, anotherFlightIdentifierEmbeddable);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstFlightIdentifierEmbeddable.hashCode(),
                sameFlightIdentifierEmbeddableAsFirstFlightIdentifierEmbeddable.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstFlightIdentifierEmbeddable.hashCode(), anotherFlightIdentifierEmbeddable.hashCode());
    }
}