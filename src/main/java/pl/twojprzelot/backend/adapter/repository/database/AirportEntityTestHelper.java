package pl.twojprzelot.backend.adapter.repository.database;

import pl.twojprzelot.backend.domain.entity.*;

class AirportEntityTestHelper {
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
    private static final int AIRPORT_ID = 4;
    private static final String AIRPORT_IATA_CODE = "AIRPORT_IATA_CODE";
    private static final String AIRPORT_ICAO_CODE = "AIRPORT_ICAO_CODE";
    private static final String AIRPORT_NAME = "AIRPORT_NAME";

    AirportEntity airportEntity;
    Airport airport;

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

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(CITY_ID);
        cityEntity.setName(CITY_NAME);
        cityEntity.setIataCode(CITY_IATA_CODE);
        cityEntity.setGeographicLocation(geographicLocationEmbeddable);
        cityEntity.setCountry(countryEntity);

        airportEntity = new AirportEntity();
        airportEntity.setId(AIRPORT_ID);
        airportEntity.setIataCode(AIRPORT_IATA_CODE);
        airportEntity.setIcaoCode(AIRPORT_ICAO_CODE);
        airportEntity.setName(AIRPORT_NAME);
        airportEntity.setGeographicLocation(geographicLocationEmbeddable);
        airportEntity.setCity(cityEntity);

        airport = Airport.builder()
                .id(AIRPORT_ID)
                .iataCode(AIRPORT_IATA_CODE)
                .icaoCode(AIRPORT_ICAO_CODE)
                .name(AIRPORT_NAME)
                .geographicLocation(GeographicLocation.builder()
                        .latitude(LATITUDE)
                        .longitude(LONGITUDE)
                        .build())
                .city(City.builder()
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
                        .build())
                .build();
    }

    void removeCurrencyEntity() {
        airportEntity.getCity()
                .getCountry()
                .setCurrency(null);
    }

    void removeCurrency() {
       airport = airport.toBuilder()
               .city(airport.getCity().toBuilder()
                       .country(airport.getCity().getCountry().toBuilder()
                               .currency(null)
                               .build())
                       .build())
               .build();
    }

    void removeCountryEntity() {
        airportEntity.getCity()
                .setCountry(null);
    }

    void removeCountry() {
        airport = airport.toBuilder()
                .city(airport.getCity().toBuilder()
                        .country(null)
                        .build())
                .build();
    }

    void removeCityGeographicLocationEmbeddable() {
        airportEntity.getCity()
                .setGeographicLocation(null);
    }

    void removeCityGeographicLocation() {
        airport = airport.toBuilder()
                .city(airport.getCity().toBuilder()
                        .geographicLocation(null)
                        .build())
                .build();
    }

    void removeGeographicLocationEmbeddable() {
        airportEntity.setGeographicLocation(null);
    }

    void removeGeographicLocation() {
        airport = airport.toBuilder()
                .geographicLocation(null)
                .build();
    }

    void removeCityEntity() {
        airportEntity.setCity(null);
    }

    void removeCity() {
        airport = airport.toBuilder()
                .city(null)
                .build();
    }
}
