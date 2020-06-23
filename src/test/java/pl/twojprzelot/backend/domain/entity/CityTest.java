package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityTest {
    private static final int ID = 5;
    private static final String NAME = "NAME";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ANOTHER_IATA_CODE = "ANOTHER_IATA_CODE";
    private static final double LATITUDE = 1.0;
    private static final double LONGITUDE = 2.0;
    private static final String COUNTRY_NAME = "COUNTRY_NAME";
    private static final int POPULATION = 10;

    private GeographicLocation geographicLocation;
    private Country country;

    @BeforeEach
    void setUp() {
        geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        country = Country.builder()
                .name(COUNTRY_NAME)
                .population(POPULATION)
                .build();
    }

    @Test
    void builderTest_id() {
        City city = City.builder()
                .id(ID)
                .build();

        assertEquals(ID, city.getId());
    }

    @Test
    void builderTest_name() {
        City city = City.builder()
                .name(NAME)
                .build();

        assertEquals(NAME, city.getName());
    }

    @Test
    void builderTest_iataCode() {
        City city = City.builder()
                .iataCode(IATA_CODE)
                .build();

        assertEquals(IATA_CODE, city.getIataCode());
    }

    @Test
    void builderTest_geographicLocation() {
        City city = City.builder()
                .geographicLocation(geographicLocation)
                .build();

        assertEquals(geographicLocation, city.getGeographicLocation());
    }

    @Test
    void builderTest_country() {
        City city = City.builder()
                .country(country)
                .build();

        assertEquals(country, city.getCountry());
    }

    @Test
    void builderTest_toBuilder() {
        City city = City.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .build();

        City anotherCity = city.toBuilder()
                .iataCode(ANOTHER_IATA_CODE)
                .build();

        City expectedCity = City.builder()
                .id(ID)
                .name(NAME)
                .iataCode(ANOTHER_IATA_CODE)
                .build();

        assertEquals(expectedCity, anotherCity);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(City.class).verify();
    }
}