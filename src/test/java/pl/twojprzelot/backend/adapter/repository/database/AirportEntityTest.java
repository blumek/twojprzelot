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

    @Test
    void fromTest_fullObject() {
        assertEquals(airportEntity, AirportEntity.from(airport));
    }

    @Test
    void fromTest_withoutCurrency() {
        removeCurrencyEntity();
        removeCurrency();

        assertEquals(airportEntity, AirportEntity.from(airport));
    }

    @Test
    void fromTest_withoutCountry() {
        removeCountryEntity();
        removeCountry();

        assertEquals(airportEntity, AirportEntity.from(airport));
    }

    @Test
    void fromTest_withoutCityGeographicLocation() {
        removeCityGeographicLocationEmbeddable();
        removeCityGeographicLocation();

        assertEquals(airportEntity, AirportEntity.from(airport));
    }

    @Test
    void fromTest_withoutGeographicLocation() {
        removeGeographicLocationEmbeddable();
        removeGeographicLocation();

        assertEquals(airportEntity, AirportEntity.from(airport));
    }

    @Test
    void fromTest_withoutCity() {
        removeCityEntity();
        removeCity();

        assertEquals(airportEntity, AirportEntity.from(airport));
    }
}