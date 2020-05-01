package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.GeographicLocation;

import static org.junit.jupiter.api.Assertions.*;

class CityAETest {

    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String COUNTRY_ISO_2_CODE = "COUNTRY_ISO_2_CODE";
    private static final String NAME = "NAME";
    private static final double LATITUDE = 5.0;
    private static final double LONGITUDE = 6.0;

    @Test
    void toCityTest() {
        CityAE cityAE = new CityAE();
        cityAE.setId(ID);
        cityAE.setIataCode(IATA_CODE);
        cityAE.setCountryIso2Code(COUNTRY_ISO_2_CODE);
        cityAE.setName(NAME);
        cityAE.setLatitude(LATITUDE);
        cityAE.setLongitude(LONGITUDE);

        City city = City.builder()
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

        assertEquals(city, cityAE.toCity());
    }
}