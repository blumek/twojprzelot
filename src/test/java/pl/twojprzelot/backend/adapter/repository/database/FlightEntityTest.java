package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightEntityTest extends FlightEntityTestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void fromTest_fullObjects() {
        assertEquals(flightEntity, FlightEntity.from(flight));
    }

    @Test
    void fromTest_withoutId() {
        removeIdEntity();
        removeId();

        assertEquals(flightEntity, FlightEntity.from(flight));
    }

    @Test
    void fromTest_withoutFlightIdentifier() {
        removeFlightIdentifierEntity();
        removeFlightIdentifier();

        assertEquals(flightEntity, FlightEntity.from(flight));
    }

    @Test
    void fromTest_withoutGeographicPosition() {
        removeGeographicPositionEntity();
        removeGeographicPosition();

        assertEquals(flightEntity, FlightEntity.from(flight));
    }

    @Test
    void fromTest_withoutAirplaneSpeed() {
        removeAirplaneSpeedEntity();
        removeAirplaneSpeed();

        assertEquals(flightEntity, FlightEntity.from(flight));
    }

    @Test
    void toFlightTest_fullObjects() {
        assertEquals(flight, flightEntity.toFlight());
    }

    @Test
    void toFlightTest_withoutId() {
        removeIdEntity();
        removeId();

        assertEquals(flight, flightEntity.toFlight());
    }

    @Test
    void toFlightTest_withoutFlightIdentifier() {
        removeFlightIdentifierEntity();
        removeFlightIdentifier();

        assertEquals(flight, flightEntity.toFlight());
    }

    @Test
    void toFlightTest_withoutGeographicPosition() {
        removeGeographicPositionEntity();
        removeGeographicPosition();

        assertEquals(flight, flightEntity.toFlight());
    }

    @Test
    void toFlightTest_withoutAirplaneSpeed() {
        removeAirplaneSpeedEntity();
        removeAirplaneSpeed();

        assertEquals(flight, flightEntity.toFlight());
    }
}