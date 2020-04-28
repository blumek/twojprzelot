package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pl.twojprzelot.backend.domain.entity.Language.POLISH;

class CityEntityTest {
    private static final String ID = "ID";
    private static final String ANOTHER_ID = "ANOTHER_ID";
    private static final String NAME = "NAME";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String NAME_TRANSLATION = "NAME_TRANSLATION";
    private static final String COUNTRY_ID = "COUNTRY_ID";
    private static final int LONGITUDE = 10;

    private CityEntity firstCityEntity;
    private CityEntity sameCityEntityAsFirstCityEntity;
    private CityEntity anotherCityEntity;

    @BeforeEach
    void setUp() {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(COUNTRY_ID);

        GeographicLocationEmbeddable geographicLocationEmbeddable = new GeographicLocationEmbeddable();
        geographicLocationEmbeddable.setLongitude(LONGITUDE);

        firstCityEntity = new CityEntity();
        firstCityEntity.setId(ID);
        firstCityEntity.setName(NAME);
        firstCityEntity.setIataCode(IATA_CODE);
        firstCityEntity.setCountry(countryEntity);
        firstCityEntity.setGeographicLocation(geographicLocationEmbeddable);
        firstCityEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);

        sameCityEntityAsFirstCityEntity = new CityEntity();
        sameCityEntityAsFirstCityEntity.setId(ID);
        sameCityEntityAsFirstCityEntity.setName(NAME);
        sameCityEntityAsFirstCityEntity.setIataCode(IATA_CODE);
        sameCityEntityAsFirstCityEntity.setCountry(countryEntity);
        sameCityEntityAsFirstCityEntity.setGeographicLocation(geographicLocationEmbeddable);
        sameCityEntityAsFirstCityEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);

        anotherCityEntity = new CityEntity();
        anotherCityEntity.setId(ANOTHER_ID);
        anotherCityEntity.setName(NAME);
        anotherCityEntity.setIataCode(IATA_CODE);
        anotherCityEntity.setCountry(countryEntity);
        anotherCityEntity.setGeographicLocation(geographicLocationEmbeddable);
        anotherCityEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstCityEntity, sameCityEntityAsFirstCityEntity);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstCityEntity, anotherCityEntity);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstCityEntity.hashCode(), sameCityEntityAsFirstCityEntity.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstCityEntity.hashCode(), anotherCityEntity.hashCode());
    }
}