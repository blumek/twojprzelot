package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineEntityTest {
    private static final String ID = "ID";
    private static final String ANOTHER_ID = "ANOTHER_ID";
    private static final String NAME = "NAME";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";

    private AirlineEntity firstAirlineEntity;
    private AirlineEntity sameAirlineEntityAsFirstAirlineEntity;
    private AirlineEntity anotherAirlineEntity;

    @BeforeEach
    void setUp() {
        firstAirlineEntity = new AirlineEntity();
        firstAirlineEntity.setId(ID);
        firstAirlineEntity.setName(NAME);
        firstAirlineEntity.setIataCode(IATA_CODE);
        firstAirlineEntity.setIcaoCode(ICAO_CODE);

        sameAirlineEntityAsFirstAirlineEntity = new AirlineEntity();
        sameAirlineEntityAsFirstAirlineEntity.setId(ID);
        sameAirlineEntityAsFirstAirlineEntity.setName(NAME);
        sameAirlineEntityAsFirstAirlineEntity.setIataCode(IATA_CODE);
        sameAirlineEntityAsFirstAirlineEntity.setIcaoCode(ICAO_CODE);

        anotherAirlineEntity = new AirlineEntity();
        anotherAirlineEntity.setId(ANOTHER_ID);
        anotherAirlineEntity.setName(NAME);
        anotherAirlineEntity.setIataCode(IATA_CODE);
        anotherAirlineEntity.setIcaoCode(ICAO_CODE);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstAirlineEntity, sameAirlineEntityAsFirstAirlineEntity);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstAirlineEntity, anotherAirlineEntity);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstAirlineEntity.hashCode(), sameAirlineEntityAsFirstAirlineEntity.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstAirlineEntity.hashCode(), anotherAirlineEntity.hashCode());
    }
}