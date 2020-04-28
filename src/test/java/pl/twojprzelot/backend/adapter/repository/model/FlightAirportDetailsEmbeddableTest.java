package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightAirportDetailsEmbeddableTest {
    private static final String GATE = "GATE";
    private static final String ANOTHER_GATE = "ANOTHER_GATE";
    private static final String TERMINAL = "TERMINAL";

    private FlightAirportDetailsEmbeddable firstFlightAirportDetailsEmbeddable;
    private FlightAirportDetailsEmbeddable sameFlightAirportDetailsEmbeddableAsFirstFlightAirportDetailsEmbeddable;
    private FlightAirportDetailsEmbeddable anotherFlightAirportDetailsEmbeddable;

    @BeforeEach
    void setUp() {
        firstFlightAirportDetailsEmbeddable = new FlightAirportDetailsEmbeddable();
        firstFlightAirportDetailsEmbeddable.setGate(GATE);
        firstFlightAirportDetailsEmbeddable.setTerminal(TERMINAL);

        sameFlightAirportDetailsEmbeddableAsFirstFlightAirportDetailsEmbeddable = new FlightAirportDetailsEmbeddable();
        sameFlightAirportDetailsEmbeddableAsFirstFlightAirportDetailsEmbeddable.setGate(GATE);
        sameFlightAirportDetailsEmbeddableAsFirstFlightAirportDetailsEmbeddable.setTerminal(TERMINAL);

        anotherFlightAirportDetailsEmbeddable = new FlightAirportDetailsEmbeddable();
        anotherFlightAirportDetailsEmbeddable.setGate(ANOTHER_GATE);
        anotherFlightAirportDetailsEmbeddable.setTerminal(TERMINAL);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstFlightAirportDetailsEmbeddable,
                sameFlightAirportDetailsEmbeddableAsFirstFlightAirportDetailsEmbeddable);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstFlightAirportDetailsEmbeddable, anotherFlightAirportDetailsEmbeddable);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstFlightAirportDetailsEmbeddable.hashCode(),
                sameFlightAirportDetailsEmbeddableAsFirstFlightAirportDetailsEmbeddable.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstFlightAirportDetailsEmbeddable.hashCode(),
                anotherFlightAirportDetailsEmbeddable.hashCode());
    }
}