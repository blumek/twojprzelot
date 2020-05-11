package pl.twojprzelot.backend.adapter.repository.database;

import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Currency;

class CountryEntityTestHelper {
    static final int ID = 1;
    static final String NAME = "NAME";
    static final String ISO_2_CODE = "ISO_2_CODE";
    static final String ISO_3_CODE = "ISO_3_CODE";
    static final int ISO_NUMBER = 10;
    static final int POPULATION = 500;
    static final int CURRENCY_ID = 2;
    static final String CURRENCY_NAME = "CURRENCY_NAME";
    static final String CURRENCY_CODE = "CURRENCY_CODE";
    static final int CURRENCY_ISO_NUMBER = 100;

    CountryEntity countryEntity;
    Country country;

    void init() {
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

    void removeCurrency() {
        country = country.toBuilder()
                .currency(null)
                .build();
    }

    void removeCurrencyEntity() {
        countryEntity.setCurrency(null);
    }
}