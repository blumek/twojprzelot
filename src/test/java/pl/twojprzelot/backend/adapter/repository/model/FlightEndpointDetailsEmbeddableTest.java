package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightEndpointDetailsEmbeddableTest {
    private static final String AIRPORT_ID = "AIRPORT_ID";
    private static final String ANOTHER_AIRPORT_ID = "ANOTHER_AIRPORT_ID";
    private static final String TERMINAL = "TERMINAL";
    private static final int YEAR = 2000;
    private static final int MONTH = 10;
    private static final int DAY_OF_MONTH = 10;
    private static final int HOUR = 10;
    private static final int MINUTE = 10;
    private static final int SECOND = 0;

    private FlightEndpointDetailsEmbeddable firstFlightEndpointDetailsEmbeddable;
    private FlightEndpointDetailsEmbeddable sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable;
    private FlightEndpointDetailsEmbeddable anotherFlightEndpointDetailsEmbeddable;

    @BeforeEach
    void setUp() {
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setId(AIRPORT_ID);

        AirportEntity anotherAirportEntity = new AirportEntity();
        airportEntity.setId(ANOTHER_AIRPORT_ID);

        FlightAirportDetailsEmbeddable flightAirportDetailsEmbeddable = new FlightAirportDetailsEmbeddable();
        flightAirportDetailsEmbeddable.setTerminal(TERMINAL);

        LocalDateTime time = LocalDateTime.of(YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE, SECOND);

        firstFlightEndpointDetailsEmbeddable = new FlightEndpointDetailsEmbeddable();
        firstFlightEndpointDetailsEmbeddable.setAirport(airportEntity);
        firstFlightEndpointDetailsEmbeddable.setFlightAirportDetails(flightAirportDetailsEmbeddable);
        firstFlightEndpointDetailsEmbeddable.setScheduledTime(time);
        firstFlightEndpointDetailsEmbeddable.setEstimatedTime(time);
        firstFlightEndpointDetailsEmbeddable.setActualTime(time);
        firstFlightEndpointDetailsEmbeddable.setEstimatedRunwayTime(time);
        firstFlightEndpointDetailsEmbeddable.setActualRunwayTime(time);

        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                = new FlightEndpointDetailsEmbeddable();
        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                .setAirport(airportEntity);
        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                .setFlightAirportDetails(flightAirportDetailsEmbeddable);
        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                .setScheduledTime(time);
        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                .setEstimatedTime(time);
        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                .setActualTime(time);
        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                .setEstimatedRunwayTime(time);
        sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable
                .setActualRunwayTime(time);

        anotherFlightEndpointDetailsEmbeddable = new FlightEndpointDetailsEmbeddable();
        anotherFlightEndpointDetailsEmbeddable.setAirport(anotherAirportEntity);
        anotherFlightEndpointDetailsEmbeddable.setFlightAirportDetails(flightAirportDetailsEmbeddable);
        anotherFlightEndpointDetailsEmbeddable.setScheduledTime(time);
        anotherFlightEndpointDetailsEmbeddable.setEstimatedTime(time);
        anotherFlightEndpointDetailsEmbeddable.setActualTime(time);
        anotherFlightEndpointDetailsEmbeddable.setEstimatedRunwayTime(time);
        anotherFlightEndpointDetailsEmbeddable.setActualRunwayTime(time);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstFlightEndpointDetailsEmbeddable,
                sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstFlightEndpointDetailsEmbeddable, anotherFlightEndpointDetailsEmbeddable);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstFlightEndpointDetailsEmbeddable.hashCode(),
                sameFlightEndpointDetailsEmbeddableAsFirstFlightEndpointDetailsEmbeddable.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstFlightEndpointDetailsEmbeddable.hashCode(),
                anotherFlightEndpointDetailsEmbeddable.hashCode());
    }
}