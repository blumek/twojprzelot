package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityEntityTest extends CityEntityTestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void toCityTest_fullObject() {
        assertEquals(city, cityEntity.toCity());
    }

    @Test
    void toCityTest_withoutCurrency() {
        removeCurrencyEntity();
        removeCurrency();

        assertEquals(city, cityEntity.toCity());
    }

    @Test
    void toCityTest_withoutCountry() {
        removeCountryEntity();
        removeCountry();

        assertEquals(city, cityEntity.toCity());
    }

    @Test
    void toCityTest_withoutGeographicLocation() {
        removeGeographicLocationEmbeddable();
        removeGeographicLocation();

        assertEquals(city, cityEntity.toCity());
    }

    @Test
    void fromTest_fullObject() {
        assertEquals(cityEntity, CityEntity.from(city));
    }

    @Test
    void fromTest_withoutCurrency() {
        removeCurrencyEntity();
        removeCurrency();

        assertEquals(cityEntity, CityEntity.from(city));
    }

    @Test
    void fromTest_withoutCountry() {
        removeCountryEntity();
        removeCountry();

        assertEquals(cityEntity, CityEntity.from(city));
    }

    @Test
    void fromTest_withoutGeographicLocation() {
        removeGeographicLocationEmbeddable();
        removeGeographicLocation();

        assertEquals(cityEntity, CityEntity.from(city));
    }
}