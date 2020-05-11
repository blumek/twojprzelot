package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryEntityTest extends CountryEntityTestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void toCountryTest_fullObject() {
        assertEquals(country, countryEntity.toCountry());
    }

    @Test
    void toCountryTest_withoutCurrency() {
        removeCurrencyEntity();
        removeCurrency();

        assertEquals(country, countryEntity.toCountry());
    }

    @Test
    void fromTest_fullObject() {
        assertEquals(countryEntity, CountryEntity.from(country));
    }

    @Test
    void fromTest_withoutCurrency() {
        removeCurrencyEntity();
        removeCurrency();

        assertEquals(countryEntity, CountryEntity.from(country));
    }
}