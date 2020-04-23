package pl.twojprzelot.backend.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeographicLocationTest {
    private static final double LATITUDE = 1.0;
    private static final double ANOTHER_LATITUDE = 1.5;
    private static final double LONGITUDE = 2.0;

    @Test
    void builderTest_latitude() {
        GeographicLocation geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .build();

        assertEquals(LATITUDE, geographicLocation.getLatitude());
    }

    @Test
    void builderTest_longitude() {
        GeographicLocation geographicLocation = GeographicLocation.builder()
                .longitude(LONGITUDE)
                .build();

        assertEquals(LONGITUDE, geographicLocation.getLongitude());
    }

    @Test
    void builderTest_toBuilder() {
        GeographicLocation geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        GeographicLocation anotherGeographicLocation = geographicLocation.toBuilder()
                .latitude(ANOTHER_LATITUDE)
                .build();

        GeographicLocation expectedGeographicLocation = GeographicLocation.builder()
                .latitude(ANOTHER_LATITUDE)
                .longitude(LONGITUDE)
                .build();

        assertEquals(expectedGeographicLocation, anotherGeographicLocation);
    }

    @Test
    void equalsTest_equalObjects() {
        GeographicLocation geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        GeographicLocation anotherGeographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        assertEquals(geographicLocation, anotherGeographicLocation);
    }

    @Test
    void equalsTest_notEqualObjects() {
        GeographicLocation geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        GeographicLocation anotherGeographicLocation = GeographicLocation.builder()
                .latitude(ANOTHER_LATITUDE)
                .longitude(LONGITUDE)
                .build();

        assertNotEquals(geographicLocation, anotherGeographicLocation);
    }

    @Test
    void hashCodeTest_equalObjects() {
        GeographicLocation geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        GeographicLocation anotherGeographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        assertEquals(geographicLocation.hashCode(), anotherGeographicLocation.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        GeographicLocation geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        GeographicLocation anotherGeographicLocation = GeographicLocation.builder()
                .latitude(ANOTHER_LATITUDE)
                .longitude(LONGITUDE)
                .build();

        assertNotEquals(geographicLocation.hashCode(), anotherGeographicLocation.hashCode());
    }
}