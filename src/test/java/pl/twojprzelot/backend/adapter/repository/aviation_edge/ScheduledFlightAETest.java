package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduledFlightAETest extends ScheduledFlightAETestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void toScheduledFlightTest_fullObject() {
        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutAirline() {
        removeAirlineAE();
        removeAirline();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutFlightIdentifier() {
        removeFlightIdentifierAE();
        removeFlightIdentifier();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDeparture() {
        removeDepartureAE();
        removeDeparture();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureAirport() {
        removeDepartureAEAirportData();
        removeDepartureAirport();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureFlightAirportDetails() {
        removeDepartureAEFlightAirportDetailsData();
        removeDepartureFlightAirportDetails();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrival() {
        removeArrivalAE();
        removeArrival();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalAirport() {
        removeArrivalAEAirportData();
        removeArrivalAirport();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalFlightAirportDetails() {
        removeArrivalAEFlightAirportDetailsData();
        removeArrivalFlightAirportDetails();

        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }
}