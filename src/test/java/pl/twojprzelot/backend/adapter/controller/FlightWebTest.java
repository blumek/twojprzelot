package pl.twojprzelot.backend.adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightWebTest extends FlightWebTestHelper {
    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void fromTest_fullObject() {
        assertEquals(flightWeb, FlightWeb.from(flight));
    }

    @Test
    void fromTest_flightIdentifierNotAvailable() {
        removeFlightIdentifierWeb();
        removeFlightIdentifier();

        assertEquals(flightWeb, FlightWeb.from(flight));
    }

    @Test
    void fromTest_geographicPositionNotAvailable() {
        removeGeographicPositionWeb();
        removeGeographicPosition();

        assertEquals(flightWeb, FlightWeb.from(flight));
    }

    @Test
    void fromTest_airplaneSpeedNotAvailable() {
        removeAirplaneSpeedWeb();
        removeAirplaneSpeed();

        assertEquals(flightWeb, FlightWeb.from(flight));
    }
}