package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightAETest extends FlightAETestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void toFlightTest_fullObject() {
        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutFlightIdentifier() {
        removeFlightIdentifierAE();
        removeFlightIdentifier();

        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutGeographicPosition() {
        removeGeographicPositionAE();
        removeGeographicPosition();

        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutAirplaneSpeed() {
        removeAirplaneSpeedAE();
        removeAirplaneSpeed();

        assertEquals(flight, flightAE.toFlight());
    }
}