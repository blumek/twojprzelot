package pl.twojprzelot.backend.adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduledFlightWebTest extends ScheduledFlightWebTestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void fromTest_fullObject() {
        assertEquals(scheduledFlightWeb, ScheduledFlightWeb.from(scheduledFlight));
    }

    @Test
    void fromTest_countryNotAvailable() {
        removeCountryFromScheduledFlight();
        removeCountryNameFromScheduledFlightWeb();

        assertEquals(scheduledFlightWeb, ScheduledFlightWeb.from(scheduledFlight));
    }

    @Test
    void fromTest_cityNotAvailable() {
        removeCityFromScheduledFlight();
        removeCityNameFromScheduledFlightWeb();

        assertEquals(scheduledFlightWeb, ScheduledFlightWeb.from(scheduledFlight));
    }

    @Test
    void fromTest_flightAirportDetailsNotAvailable() {
        removeFlightAirportDetailsFromScheduledFlight();
        removeGateAndTerminalFromScheduledFlightWeb();

        assertEquals(scheduledFlightWeb, ScheduledFlightWeb.from(scheduledFlight));
    }
}