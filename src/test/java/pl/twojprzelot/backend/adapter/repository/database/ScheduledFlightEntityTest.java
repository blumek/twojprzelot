package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduledFlightEntityTest extends ScheduledFlightEntityTestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void fromTest_fullObjects() {
        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutFlightIdentifier() {
        removeFlightIdentifierEmbeddable();
        removeFlightIdentifier();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDeparture() {
        removeDepartureEntity();
        removeDeparture();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDepartureAirport() {
        removeDepartureEntityAirport();
        removeDepartureAirport();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDepartureAirportCity() {
        removeDepartureEntityAirportCity();
        removeDepartureAirportCity();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDepartureAirportGeographicLocation() {
        removeDepartureEntityAirportGeographicLocation();
        removeDepartureAirportGeographicLocation();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDepartureAirportCityCountry() {
        removeDepartureEntityAirportCityCountry();
        removeDepartureAirportCityCountry();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDepartureAirportCityGeographicLocation() {
        removeDepartureEntityAirportCityGeographicLocation();
        removeDepartureAirportCityGeographicLocation();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDepartureAirportCityCountryCurrency() {
        removeDepartureEntityAirportCityCountryCurrency();
        removeDepartureAirportCityCountryCurrency();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDepartureFlightAirportDetails() {
        removeDepartureEntityFlightAirportDetails();
        removeDepartureFlightAirportDetails();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrival() {
        removeArrivalEntity();
        removeArrival();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrivalAirport() {
        removeArrivalEntityAirport();
        removeArrivalAirport();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrivalAirportCity() {
        removeArrivalEntityAirportCity();
        removeArrivalAirportCity();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrivalAirportGeographicLocation() {
        removeArrivalEntityAirportGeographicLocation();
        removeArrivalAirportGeographicLocation();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrivalAirportCityCountry() {
        removeArrivalEntityAirportCityCountry();
        removeArrivalAirportCityCountry();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrivalAirportCityGeographicLocation() {
        removeArrivalEntityAirportCityGeographicLocation();
        removeArrivalAirportCityGeographicLocation();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrivalAirportCityCountryCurrency() {
        removeArrivalEntityAirportCityCountryCurrency();
        removeArrivalAirportCityCountryCurrency();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrivalFlightAirportDetails() {
        removeArrivalEntityFlightAirportDetails();
        removeArrivalFlightAirportDetails();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutAirline() {
        removeAirlineEntity();
        removeAirline();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_fullObjects() {
        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutFlightIdentifier() {
        removeFlightIdentifierEmbeddable();
        removeFlightIdentifier();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDeparture() {
        removeDepartureEntity();
        removeDeparture();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureAirport() {
        removeDepartureEntityAirport();
        removeDepartureAirport();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureAirportCity() {
        removeDepartureEntityAirportCity();
        removeDepartureAirportCity();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureAirportGeographicLocation() {
        removeDepartureEntityAirportGeographicLocation();
        removeDepartureAirportGeographicLocation();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureAirportCityCountry() {
        removeDepartureEntityAirportCityCountry();
        removeDepartureAirportCityCountry();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureAirportCityGeographicLocation() {
        removeDepartureEntityAirportCityGeographicLocation();
        removeDepartureAirportCityGeographicLocation();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureAirportCityCountryCurrency() {
        removeDepartureEntityAirportCityCountryCurrency();
        removeDepartureAirportCityCountryCurrency();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutDepartureFlightAirportDetails() {
        removeDepartureEntityFlightAirportDetails();
        removeDepartureFlightAirportDetails();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrival() {
        removeArrivalEntity();
        removeArrival();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalAirport() {
        removeArrivalEntityAirport();
        removeArrivalAirport();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalAirportCity() {
        removeArrivalEntityAirportCity();
        removeArrivalAirportCity();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalAirportGeographicLocation() {
        removeArrivalEntityAirportGeographicLocation();
        removeArrivalAirportGeographicLocation();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalAirportCityCountry() {
        removeArrivalEntityAirportCityCountry();
        removeArrivalAirportCityCountry();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalAirportCityGeographicLocation() {
        removeArrivalEntityAirportCityGeographicLocation();
        removeArrivalAirportCityGeographicLocation();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalAirportCityCountryCurrency() {
        removeArrivalEntityAirportCityCountryCurrency();
        removeArrivalAirportCityCountryCurrency();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutArrivalFlightAirportDetails() {
        removeArrivalEntityFlightAirportDetails();
        removeArrivalFlightAirportDetails();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutAirline() {
        removeAirlineEntity();
        removeAirline();

        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }
}