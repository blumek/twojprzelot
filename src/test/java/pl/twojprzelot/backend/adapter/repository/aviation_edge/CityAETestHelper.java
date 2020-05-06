package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.GeographicLocation;

class CityAETestHelper {
    static final int ID = 1;
    static final String IATA_CODE = "IATA_CODE";
    static final String COUNTRY_ISO_2_CODE = "COUNTRY_ISO_2_CODE";
    static final String NAME = "NAME";
    static final double LATITUDE = 5.0;
    static final double LONGITUDE = 6.0;

    CityAE cityAE;
    City city;

    void init() {
        cityAE = new CityAE();
        cityAE.setId(ID);
        cityAE.setIataCode(IATA_CODE);
        cityAE.setCountryIso2Code(COUNTRY_ISO_2_CODE);
        cityAE.setName(NAME);
        cityAE.setLatitude(LATITUDE);
        cityAE.setLongitude(LONGITUDE);

        city = City.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .name(NAME)
                .country(Country.builder()
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .build())
                .geographicLocation(GeographicLocation.builder()
                        .latitude(LATITUDE)
                        .longitude(LONGITUDE)
                        .build())
                .build();
    }

    void removeCityAEGeographicLocationData() {
        cityAE.setLatitude(0.0);
        cityAE.setLongitude(0.0);
    }

    void removeCityGeographicLocation() {
        city = city.toBuilder()
                .geographicLocation(null)
                .build();
    }

    void removeCityAECountryData() {
        cityAE.setCountryIso2Code(null);
    }

    void removeCityCountry() {
        city = city.toBuilder()
                .country(null)
                .build();
    }
}