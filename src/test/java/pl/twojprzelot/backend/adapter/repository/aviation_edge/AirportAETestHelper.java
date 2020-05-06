package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.GeographicLocation;

class AirportAETestHelper {
    static final int ID = 1;
    static final String NAME = "NAME";
    static final String IATA_CODE = "IATA_CODE";
    static final String ICAO_CODE = "ICAO_CODE";
    static final double LATITUDE = 10.5;
    static final double LONGITUDE = 12.5;
    static final String CITY_IATA_CODE = "CITY_IATA_CODE";
    static final String COUNTRY_NAME = "COUNTRY_NAME";
    static final String COUNTRY_ISO_2_CODE = "COUNTRY_ISO_2_CODE";

    AirportAE airportAE;
    Airport airport;

    void init() {
        airportAE = new AirportAE();
        airportAE.setId(ID);
        airportAE.setName(NAME);
        airportAE.setIataCode(IATA_CODE);
        airportAE.setIcaoCode(ICAO_CODE);
        airportAE.setLatitude(LATITUDE);
        airportAE.setLongitude(LONGITUDE);
        airportAE.setCityIataCode(CITY_IATA_CODE);
        airportAE.setCountryName(COUNTRY_NAME);
        airportAE.setCountryIso2Code(COUNTRY_ISO_2_CODE);

        airport = Airport.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .geographicLocation(GeographicLocation.builder()
                        .latitude(LATITUDE)
                        .longitude(LONGITUDE)
                        .build())
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .country(Country.builder()
                                .name(COUNTRY_NAME)
                                .iso2Code(COUNTRY_ISO_2_CODE)
                                .build())
                        .build())
                .build();
    }

    void removeCityAEData() {
        airportAE.setCityIataCode(null);
        removeCityAECountryData();
    }

    void removeCity() {
        airport = airport.toBuilder()
                .city(null)
                .build();
    }

    void removeCityAECountryData() {
        airportAE.setCountryIso2Code(null);
        airportAE.setCountryName(null);
    }

    void removeCityCountry() {
        airport = airport.toBuilder()
                .city(airport.getCity().toBuilder()
                        .country(null)
                        .build())
                .build();
    }
}