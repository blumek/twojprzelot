package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityAETest extends CityAETestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void toCityTest_fullObject() {
        assertEquals(city, cityAE.toCity());
    }

    @Test
    void toCityTest_withoutGeographicLocationData() {
        removeCityAEGeographicLocationData();
        removeCityGeographicLocation();

        assertEquals(city, cityAE.toCity());
    }

    @Test
    void toCityTest_withoutCountryData() {
        removeCityAECountryData();
        removeCityCountry();

        assertEquals(city, cityAE.toCity());
    }
}