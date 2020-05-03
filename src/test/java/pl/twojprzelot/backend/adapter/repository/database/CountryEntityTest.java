package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Currency;

import static org.junit.jupiter.api.Assertions.*;

class CountryEntityTest {
    private static final int ID = 1;
    private static final String NAME = "NAME";
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final String ISO_3_CODE = "ISO_3_CODE";
    private static final int ISO_NUMBER = 10;
    private static final int POPULATION = 500;
    private static final int CURRENCY_ID = 2;
    private static final String CURRENCY_NAME = "CURRENCY_NAME";
    private static final String CURRENCY_CODE = "CURRENCY_CODE";
    private static final int CURRENCY_ISO_NUMBER = 100;
    private CountryEntity countryEntity;
    private Country country;

    @BeforeEach
    void setUp() {
        countryEntity = new CountryEntity();
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(CURRENCY_ID);
        currencyEntity.setName(CURRENCY_NAME);
        currencyEntity.setCode(CURRENCY_CODE);
        currencyEntity.setIsoNumber(CURRENCY_ISO_NUMBER);

        countryEntity.setId(ID);
        countryEntity.setName(NAME);
        countryEntity.setIso2Code(ISO_2_CODE);
        countryEntity.setIso3Code(ISO_3_CODE);
        countryEntity.setIsoNumber(ISO_NUMBER);
        countryEntity.setPopulation(POPULATION);
        countryEntity.setCurrency(currencyEntity);

        country = Country.builder()
                .id(ID)
                .name(NAME)
                .iso2Code(ISO_2_CODE)
                .iso3Code(ISO_3_CODE)
                .isoNumber(ISO_NUMBER)
                .population(POPULATION)
                .currency(Currency.builder()
                        .id(CURRENCY_ID)
                        .name(CURRENCY_NAME)
                        .code(CURRENCY_CODE)
                        .isoNumber(CURRENCY_ISO_NUMBER)
                        .build())
                .build();
    }

    @Test
    void toCountryTest_fullObject() {
        assertEquals(country, countryEntity.toCountry());
    }

    @Test
    void toCountryTest_withoutCurrency() {
        countryEntity.setCurrency(null);

        country = country.toBuilder()
                .currency(null)
                .build();

        assertEquals(country, countryEntity.toCountry());
    }
}