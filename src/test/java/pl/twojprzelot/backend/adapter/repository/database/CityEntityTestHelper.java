package pl.twojprzelot.backend.adapter.repository.database;

import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.entity.GeographicLocation;

class CityEntityTestHelper {
    static final int CITY_ID = 1;
    static final String CITY_NAME = "CITY_NAME";
    static final String CITY_IATA_CODE = "CITY_IATA_CODE";
    static final double LATITUDE = 10.5;
    static final double LONGITUDE = 15.5;
    static final int COUNTRY_ID = 2;
    static final String COUNTRY_NAME = "COUNTRY_NAME";
    static final String COUNTRY_ISO_2_CODE = "ISO_2_CODE";
    static final String COUNTRY_ISO_3_CODE = "ISO_3_CODE";
    static final int COUNTRY_ISO_NUMBER = 100;
    static final int POPULATION = 500;
    static final int CURRENCY_ID = 3;
    static final String CURRENCY_NAME = "CURRENCY_NAME";
    static final int CURRENCY_ISO_NUMBER = 200;
    static final String CURRENCY_CODE = "CURRENCY_CODE";

    CityEntity cityEntity;
    City city;

    void init() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(CURRENCY_ID);
        currencyEntity.setName(CURRENCY_NAME);
        currencyEntity.setIsoNumber(CURRENCY_ISO_NUMBER);
        currencyEntity.setCode(CURRENCY_CODE);

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(COUNTRY_ID);
        countryEntity.setName(COUNTRY_NAME);
        countryEntity.setIso2Code(COUNTRY_ISO_2_CODE);
        countryEntity.setIso3Code(COUNTRY_ISO_3_CODE);
        countryEntity.setIsoNumber(COUNTRY_ISO_NUMBER);
        countryEntity.setPopulation(POPULATION);
        countryEntity.setCurrency(currencyEntity);

        GeographicLocationEmbeddable geographicLocationEmbeddable = new GeographicLocationEmbeddable();
        geographicLocationEmbeddable.setLatitude(LATITUDE);
        geographicLocationEmbeddable.setLongitude(LONGITUDE);

        cityEntity = new CityEntity();
        cityEntity.setId(CITY_ID);
        cityEntity.setName(CITY_NAME);
        cityEntity.setIataCode(CITY_IATA_CODE);
        cityEntity.setGeographicLocation(geographicLocationEmbeddable);
        cityEntity.setCountry(countryEntity);

        city = City.builder()
                .id(CITY_ID)
                .name(CITY_NAME)
                .iataCode(CITY_IATA_CODE)
                .geographicLocation(GeographicLocation.builder()
                        .latitude(LATITUDE)
                        .longitude(LONGITUDE)
                        .build())
                .country(Country.builder()
                        .id(COUNTRY_ID)
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .iso3Code(COUNTRY_ISO_3_CODE)
                        .isoNumber(COUNTRY_ISO_NUMBER)
                        .name(COUNTRY_NAME)
                        .population(POPULATION)
                        .currency(Currency.builder()
                                .id(CURRENCY_ID)
                                .code(CURRENCY_CODE)
                                .isoNumber(CURRENCY_ISO_NUMBER)
                                .name(CURRENCY_NAME)
                                .build())
                        .build())
                .build();
    }
    

    void removeCurrencyEntity() {
        CountryEntity countryEntity = cityEntity.getCountry();
        countryEntity.setCurrency(null);
        cityEntity.setCountry(countryEntity);
    }

    void removeCurrency() {
        city = city.toBuilder()
                .country(city.getCountry().toBuilder()
                        .currency(null)
                        .build())
                .build();
    }

    void removeCountryEntity() {
        cityEntity.setCountry(null);
    }

    void removeCountry() {
        city = city.toBuilder()
                .country(null)
                .build();
    }

    void removeGeographicLocationEmbeddable() {
        cityEntity.setGeographicLocation(null);
    }

    void removeGeographicLocation() {
        city = city.toBuilder()
                .geographicLocation(null)
                .build();
    }
}