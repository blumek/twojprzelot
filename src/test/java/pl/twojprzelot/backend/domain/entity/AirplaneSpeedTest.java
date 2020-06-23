package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneSpeedTest {
    private static final double HORIZONTAL_SPEED = 100;
    private static final int VERTICAL_SPEED = 200;

    @Test
    void builderTest_horizontalSpeed() {
        AirplaneSpeed airplaneSpeed = AirplaneSpeed.builder()
                .horizontalSpeed(HORIZONTAL_SPEED)
                .build();

        assertEquals(HORIZONTAL_SPEED, airplaneSpeed.getHorizontalSpeed());
    }

    @Test
    void builderTest_verticalSpeed() {
        AirplaneSpeed airplaneSpeed = AirplaneSpeed.builder()
                .verticalSpeed(VERTICAL_SPEED)
                .build();

        assertEquals(VERTICAL_SPEED, airplaneSpeed.getVerticalSpeed());
    }

    @Test
    void builderTest_toBuilder() {
        AirplaneSpeed airplaneSpeed = AirplaneSpeed.builder()
                .horizontalSpeed(HORIZONTAL_SPEED)
                .build();

        AirplaneSpeed modifiedAirplaneSpeed = airplaneSpeed.toBuilder()
                .verticalSpeed(VERTICAL_SPEED)
                .build();

        AirplaneSpeed expectedAirplaneSpeed = AirplaneSpeed.builder()
                .horizontalSpeed(HORIZONTAL_SPEED)
                .verticalSpeed(VERTICAL_SPEED)
                .build();

        assertEquals(expectedAirplaneSpeed, modifiedAirplaneSpeed);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(Airport.class).verify();
    }
}