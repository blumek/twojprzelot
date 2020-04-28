package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static pl.twojprzelot.backend.domain.entity.Language.POLISH;

class AirportEntityTest {
    private static final String ID = "ID";
    private static final String ANOTHER_ID = "ANOTHER_ID";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final String NAME_TRANSLATION = "NAME_TRANSLATION";
    private static final String NAME = "NAME";
    private static final String CITY_ID = "CITY_ID";
    private static final double LONGITUDE = 10.0;

    private AirportEntity firstAirportEntity;
    private AirportEntity sameAirportEntityAsFirstAirportEntity;
    private AirportEntity anotherAirportEntity;

    @BeforeEach
    void setUp() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(CITY_ID);

        GeographicLocationEmbeddable geographicLocationEmbeddable = new GeographicLocationEmbeddable();
        geographicLocationEmbeddable.setLongitude(LONGITUDE);

        firstAirportEntity = new AirportEntity();
        firstAirportEntity.setId(ID);
        firstAirportEntity.setIataCode(IATA_CODE);
        firstAirportEntity.setIcaoCode(ICAO_CODE);
        firstAirportEntity.setName(NAME);
        firstAirportEntity.setCity(cityEntity);
        firstAirportEntity.setGeographicLocation(geographicLocationEmbeddable);
        firstAirportEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);

        sameAirportEntityAsFirstAirportEntity = new AirportEntity();
        sameAirportEntityAsFirstAirportEntity.setId(ID);
        sameAirportEntityAsFirstAirportEntity.setIataCode(IATA_CODE);
        sameAirportEntityAsFirstAirportEntity.setIcaoCode(ICAO_CODE);
        sameAirportEntityAsFirstAirportEntity.setName(NAME);
        sameAirportEntityAsFirstAirportEntity.setCity(cityEntity);
        sameAirportEntityAsFirstAirportEntity.setGeographicLocation(geographicLocationEmbeddable);
        sameAirportEntityAsFirstAirportEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);

        anotherAirportEntity = new AirportEntity();
        anotherAirportEntity.setId(ANOTHER_ID);
        anotherAirportEntity.setIataCode(IATA_CODE);
        anotherAirportEntity.setIcaoCode(ICAO_CODE);
        anotherAirportEntity.setName(NAME);
        anotherAirportEntity.setCity(cityEntity);
        anotherAirportEntity.setGeographicLocation(geographicLocationEmbeddable);
        anotherAirportEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstAirportEntity, sameAirportEntityAsFirstAirportEntity);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstAirportEntity, anotherAirportEntity);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstAirportEntity.hashCode(), sameAirportEntityAsFirstAirportEntity.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstAirportEntity.hashCode(), anotherAirportEntity.hashCode());
    }
}