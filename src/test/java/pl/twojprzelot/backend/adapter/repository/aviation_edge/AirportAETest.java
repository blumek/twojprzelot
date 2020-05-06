package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirportAETest extends AirportAETestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void toAirportTest_fullObject() {
        assertEquals(airport, airportAE.toAirport());
    }

    @Test
    void toAirportTest_withoutCity() {
        removeCityAEData();
        removeCity();

        assertEquals(airport, airportAE.toAirport());
    }

    @Test
    void toAirportTest_withoutCityCountry() {
        removeCityAECountryData();
        removeCityCountry();

        assertEquals(airport, airportAE.toAirport());
    }
}