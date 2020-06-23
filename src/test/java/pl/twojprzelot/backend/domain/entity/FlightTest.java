package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private static final int ID = 1;
    private static final String FLIGHT_IATA_NUMBER = "FLIGHT_IATA_NUMBER";
    private static final double LATITUDE = 2.0;
    private static final double LONGITUDE = 3.0;
    private static final double ALTITUDE = 4.0;
    private static final int HORIZONTAL_SPEED = 100;
    private static final int VERTICAL_SPEED = 200;

    private FlightIdentifier flightIdentifier;
    private GeographicPosition geographicPosition;
    private AirplaneSpeed airplaneSpeed;

    @BeforeEach
    void setUp() {
        flightIdentifier = FlightIdentifier.builder()
                .iataNumber(FLIGHT_IATA_NUMBER)
                .build();

        geographicPosition = GeographicPosition.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .altitude(ALTITUDE)
                .build();

        airplaneSpeed = AirplaneSpeed.builder()
                .horizontalSpeed(HORIZONTAL_SPEED)
                .verticalSpeed(VERTICAL_SPEED)
                .build();
    }

    @Test
    void builderTest_id() {
        Flight flight = Flight.builder()
                .id(ID)
                .build();

        assertEquals(ID, flight.getId());
    }

    @Test
    void builderTest_flightIdentifier() {
        Flight flight = Flight.builder()
                .flightIdentifier(flightIdentifier)
                .build();

        assertEquals(flightIdentifier, flight.getFlightIdentifier());
    }

    @Test
    void builderTest_geographicPosition() {
        Flight flight = Flight.builder()
                .geographicPosition(geographicPosition)
                .build();

        assertEquals(geographicPosition, flight.getGeographicPosition());
    }

    @Test
    void builderTest_airplaneSpeed() {
        Flight flight = Flight.builder()
                .airplaneSpeed(airplaneSpeed)
                .build();

        assertEquals(airplaneSpeed, flight.getAirplaneSpeed());
    }

    @Test
    void builderTest_toBuilder() {
        Flight flight = Flight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .build();

        Flight modifiedFlight = flight.toBuilder()
                .airplaneSpeed(airplaneSpeed)
                .build();

        Flight expectedFlight = Flight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .airplaneSpeed(airplaneSpeed)
                .build();

        assertEquals(expectedFlight, modifiedFlight);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(Flight.class).verify();
    }
}