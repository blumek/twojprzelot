package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeographicPositionEmbeddableTest {
    private static final int LATITUDE = 10;
    private static final int ANOTHER_LATITUDE = 5;
    private static final int LONGITUDE = 20;
    private static final int ALTITUDE = 30;
    private static final int DIRECTION = 40;

    private GeographicPositionEmbeddable firstGeographicPositionEmbeddable;
    private GeographicPositionEmbeddable sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable;
    private GeographicPositionEmbeddable anotherGeographicPositionEmbeddable;

    @BeforeEach
    void setUp() {
        firstGeographicPositionEmbeddable = new GeographicPositionEmbeddable();
        firstGeographicPositionEmbeddable.setLatitude(LATITUDE);
        firstGeographicPositionEmbeddable.setLongitude(LONGITUDE);
        firstGeographicPositionEmbeddable.setAltitude(ALTITUDE);
        firstGeographicPositionEmbeddable.setDirection(DIRECTION);

        sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable = new GeographicPositionEmbeddable();
        sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable.setLatitude(LATITUDE);
        sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable.setLongitude(LONGITUDE);
        sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable.setAltitude(ALTITUDE);
        sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable.setDirection(DIRECTION);

        anotherGeographicPositionEmbeddable = new GeographicPositionEmbeddable();
        anotherGeographicPositionEmbeddable.setLatitude(ANOTHER_LATITUDE);
        anotherGeographicPositionEmbeddable.setLongitude(LONGITUDE);
        anotherGeographicPositionEmbeddable.setAltitude(ALTITUDE);
        anotherGeographicPositionEmbeddable.setDirection(DIRECTION);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstGeographicPositionEmbeddable,
                sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstGeographicPositionEmbeddable, anotherGeographicPositionEmbeddable);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstGeographicPositionEmbeddable.hashCode(),
                sameGeographicPositionEmbeddableAsFirstGeographicPositionEmbeddable.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstGeographicPositionEmbeddable.hashCode(), anotherGeographicPositionEmbeddable.hashCode());
    }
}