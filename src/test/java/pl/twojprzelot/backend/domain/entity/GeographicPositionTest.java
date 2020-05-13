package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeographicPositionTest {
    private static final double LATITUDE = 1.0;
    private static final double ANOTHER_LATITUDE = 1.5;
    private static final double LONGITUDE = 2.0;
    private static final double ALTITUDE = 3.0;
    private static final double DIRECTION = 4.0;

    private GeographicPosition firstGeographicPosition;
    private GeographicPosition sameGeographicPositionAsFirstGeographicPosition;
    private GeographicPosition anotherGeographicPosition;

    @BeforeEach
    void setUp() {
        firstGeographicPosition = GeographicPosition.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .altitude(ALTITUDE)
                .direction(DIRECTION)
                .build();

        sameGeographicPositionAsFirstGeographicPosition = GeographicPosition.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .altitude(ALTITUDE)
                .direction(DIRECTION)
                .build();

        anotherGeographicPosition = GeographicPosition.builder()
                .latitude(ANOTHER_LATITUDE)
                .longitude(LONGITUDE)
                .altitude(ALTITUDE)
                .direction(DIRECTION)
                .build();
    }

    @Test
    void builderTest_latitude() {
        GeographicPosition geographicPosition = GeographicPosition.builder()
                .latitude(LATITUDE)
                .build();

        assertEquals(LATITUDE, geographicPosition.getLatitude());
    }

    @Test
    void builderTest_longitude() {
        GeographicPosition geographicPosition = GeographicPosition.builder()
                .longitude(LONGITUDE)
                .build();

        assertEquals(LONGITUDE, geographicPosition.getLongitude());
    }

    @Test
    void builderTest_altitude() {
        GeographicPosition geographicPosition = GeographicPosition.builder()
                .altitude(ALTITUDE)
                .build();

        assertEquals(ALTITUDE, geographicPosition.getAltitude());
    }

    @Test
    void builderTest_direction() {
        GeographicPosition geographicPosition = GeographicPosition.builder()
                .direction(DIRECTION)
                .build();

        assertEquals(DIRECTION, geographicPosition.getDirection());
    }

    @Test
    void builderTest_toBuilder() {
        GeographicPosition geographicPosition = GeographicPosition.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .altitude(ALTITUDE)
                .direction(DIRECTION)
                .build();

        GeographicPosition anotherGeographicPosition = geographicPosition.toBuilder()
                .latitude(ANOTHER_LATITUDE)
                .build();

        GeographicPosition expectedGeographicPosition = GeographicPosition.builder()
                .latitude(ANOTHER_LATITUDE)
                .longitude(LONGITUDE)
                .altitude(ALTITUDE)
                .direction(DIRECTION)
                .build();

        assertEquals(expectedGeographicPosition, anotherGeographicPosition);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(GeographicPosition.class).verify();
    }
}