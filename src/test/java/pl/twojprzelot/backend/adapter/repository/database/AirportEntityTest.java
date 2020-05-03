package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirportEntityTest extends AirportEntityTestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void toAirportTest_fullObject() {
        assertEquals(airport, airportEntity.toAirport());
    }

    @Test
    void toAirportTest_withoutCurrency() {
        removeCurrencyEntity();
        removeCurrency();

        assertEquals(airport, airportEntity.toAirport());
    }

    @Test
    void toAirportTest_withoutCountry() {
        removeCountryEntity();
        removeCountry();

        assertEquals(airport, airportEntity.toAirport());
    }

    @Test
    void toAirportTest_withoutCityGeographicLocation() {
        removeCityGeographicLocationEmbeddable();
        removeCityGeographicLocation();

        assertEquals(airport, airportEntity.toAirport());
    }

    @Test
    void toAirportTest_withoutGeographicLocation() {
        removeGeographicLocationEmbeddable();
        removeGeographicLocation();

        assertEquals(airport, airportEntity.toAirport());
    }

    @Test
    void toAirportTest_withoutCity() {
        removeCityEntity();
        removeCity();

        assertEquals(airport, airportEntity.toAirport());
    }
}