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
    void toFlightTest_withoutAirline() {
        removeAirlineAE();
        removeAirline();

        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutFlightIdentifier() {
        removeFlightIdentifierAE();
        removeFlightIdentifier();

        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutDeparture() {
        removeDepartureAE();
        removeDeparture();

        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutDepartureData() {
        removeDepartureAEData();
        removeDeparture();

        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutArrival() {
        removeArrivalAE();
        removeArrival();

        assertEquals(flight, flightAE.toFlight());
    }

    @Test
    void toFlightTest_withoutArrivalData() {
        removeArrivalAEData();
        removeArrival();

        assertEquals(flight, flightAE.toFlight());
    }
}