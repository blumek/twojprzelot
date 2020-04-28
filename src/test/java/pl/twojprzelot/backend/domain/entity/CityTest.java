package pl.twojprzelot.backend.domain.entity;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.*;
import static pl.twojprzelot.backend.domain.entity.Language.POLISH;

class CityTest {
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ANOTHER_IATA_CODE = "ANOTHER_IATA_CODE";
    private static final double LATITUDE = 1.0;
    private static final double LONGITUDE = 2.0;
    private static final String COUNTRY_NAME = "COUNTRY_NAME";
    private static final int POPULATION = 10;
    private static final String NAME_TRANSLATION = "NAME_TRANSLATION";

    private City firstCity;
    private City sameCityAsFirstCity;
    private City anotherCity;

    private GeographicLocation geographicLocation;
    private Country country;
    private Map<Language, String> nameTranslations;

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

        nameTranslations = Maps.newHashMap();

        firstCity = City.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .geographicLocation(geographicLocation)
                .country(country)
                .nameTranslations(nameTranslations)
                .build();

        sameCityAsFirstCity = City.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .geographicLocation(geographicLocation)
                .country(country)
                .nameTranslations(nameTranslations)
                .build();

        anotherCity = City.builder()
                .id(ID)
                .name(NAME)
                .iataCode(ANOTHER_IATA_CODE)
                .geographicLocation(geographicLocation)
                .country(country)
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
    void builderTest_nameTranslations() {
        nameTranslations.put(POLISH, NAME_TRANSLATION);

        City city = City.builder()
                .nameTranslations(nameTranslations)
                .build();

        assertThat(city.getNameTranslations(), hasEntry(POLISH, NAME_TRANSLATION));
    }

    @Test
    void builderTest_addSingularNameTranslation() {
        City city = City.builder()
                .nameTranslation(POLISH, NAME_TRANSLATION)
                .build();

        assertThat(city.getNameTranslations(), hasEntry(POLISH, NAME_TRANSLATION));
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
    void equalsTest_equalObjects() {
        assertEquals(firstCity, sameCityAsFirstCity);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstCity, anotherCity);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstCity.hashCode(), sameCityAsFirstCity.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstCity.hashCode(), anotherCity.hashCode());
    }
}