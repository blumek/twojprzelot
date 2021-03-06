package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryAETest {
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final String ISO_3_CODE = "ISO_3_CODE";
    private static final String ISO_NUMBER_TEXT = "123";
    private static final int ISO_NUMBER = 123;
    private static final String POPULATION_TEXT = "1000";
    private static final int POPULATION = 1000;
    private static final String NAME = "NAME";
    private static final int ID = 1;

    private CountryAE countryAE;
    private Country country;

    @BeforeEach
    void setUp() {
        countryAE = new CountryAE();
        countryAE.setId(ID);
        countryAE.setIso2Code(ISO_2_CODE);
        countryAE.setIso3Code(ISO_3_CODE);
        countryAE.setIsoNumber(ISO_NUMBER_TEXT);
        countryAE.setPopulation(POPULATION_TEXT);
        countryAE.setName(NAME);

        country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .iso3Code(ISO_3_CODE)
                .isoNumber(ISO_NUMBER)
                .population(POPULATION)
                .name(NAME)
                .build();
    }

    @Test
    void toCountryTest_fullObject() {
        assertEquals(country, countryAE.toCountry());
    }

    @Test
    void toCountryTest_withoutCurrency() {
        removeCountryAECurrencyData();
        removeCountryCurrency();

        assertEquals(country, countryAE.toCountry());
    }

    private void removeCountryAECurrencyData() {
        countryAE.setCurrencyName(null);
        countryAE.setCurrencyCode(null);
    }

    private void removeCountryCurrency() {
        country = country.toBuilder()
                .currency(null)
                .build();
    }
}