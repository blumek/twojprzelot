package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeographicLocationEmbeddableTest {
    private static final int LATITUDE = 10;
    private static final int ANOTHER_LATITUDE = 5;
    private static final int LONGITUDE = 20;

    private GeographicLocationEmbeddable firstGeographicLocationEmbeddable;
    private GeographicLocationEmbeddable sameGeographicLocationEmbeddableAsFirstGeographicLocationEmbeddable;
    private GeographicLocationEmbeddable anotherGeographicLocationEmbeddable;

    @BeforeEach
    void setUp() {
        firstGeographicLocationEmbeddable = new GeographicLocationEmbeddable();
        firstGeographicLocationEmbeddable.setLatitude(LATITUDE);
        firstGeographicLocationEmbeddable.setLongitude(LONGITUDE);

        sameGeographicLocationEmbeddableAsFirstGeographicLocationEmbeddable = new GeographicLocationEmbeddable();
        sameGeographicLocationEmbeddableAsFirstGeographicLocationEmbeddable.setLatitude(LATITUDE);
        sameGeographicLocationEmbeddableAsFirstGeographicLocationEmbeddable.setLongitude(LONGITUDE);

        anotherGeographicLocationEmbeddable = new GeographicLocationEmbeddable();
        anotherGeographicLocationEmbeddable.setLatitude(ANOTHER_LATITUDE);
        anotherGeographicLocationEmbeddable.setLongitude(LONGITUDE);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstGeographicLocationEmbeddable,
                sameGeographicLocationEmbeddableAsFirstGeographicLocationEmbeddable);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstGeographicLocationEmbeddable, anotherGeographicLocationEmbeddable);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstGeographicLocationEmbeddable.hashCode(),
                sameGeographicLocationEmbeddableAsFirstGeographicLocationEmbeddable.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstGeographicLocationEmbeddable.hashCode(), anotherGeographicLocationEmbeddable.hashCode());
    }
}