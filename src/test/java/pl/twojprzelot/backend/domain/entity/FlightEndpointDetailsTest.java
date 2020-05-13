package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightEndpointDetailsTest {
    private static final int DELAY_MINUTES = 5;
    private static final String AIRPORT_NAME = "AIRPORT_NAME";
    private static final String ANOTHER_AIRPORT_NAME = "ANOTHER_AIRPORT_NAME";
    private static final String TERMINAL = "TERMINAL";
    private static final int YEAR = 2000;
    private static final int MONTH = 10;
    private static final int DAY_OF_MONTH = 10;
    private static final int HOUR = 10;
    private static final int MINUTE = 10;
    private static final int SECOND = 0;

    private FlightEndpointDetails firstFlightEndpointDetails;
    private FlightEndpointDetails sameFlightEndpointDetailsAsFirstFlightEndpointDetails;
    private FlightEndpointDetails anotherFlightEndpointDetails;

    private Airport airport;
    private FlightAirportDetails flightAirportDetails;
    private LocalDateTime time;

    @BeforeEach
    void setUp() {
        airport = Airport.builder()
                .name(AIRPORT_NAME)
                .build();

        Airport anotherAirport = Airport.builder()
                .name(ANOTHER_AIRPORT_NAME)
                .build();

        flightAirportDetails = FlightAirportDetails.builder()
                .terminal(TERMINAL)
                .build();

        time = LocalDateTime.of(YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE, SECOND);

        firstFlightEndpointDetails = FlightEndpointDetails.builder()
                .airport(airport)
                .flightAirportDetails(flightAirportDetails)
                .delayMinutes(DELAY_MINUTES)
                .scheduledTime(time)
                .estimatedTime(time)
                .actualTime(time)
                .estimatedRunwayTime(time)
                .actualRunwayTime(time)
                .build();

        sameFlightEndpointDetailsAsFirstFlightEndpointDetails = FlightEndpointDetails.builder()
                .airport(airport)
                .flightAirportDetails(flightAirportDetails)
                .delayMinutes(DELAY_MINUTES)
                .scheduledTime(time)
                .estimatedTime(time)
                .actualTime(time)
                .estimatedRunwayTime(time)
                .actualRunwayTime(time)
                .build();

        anotherFlightEndpointDetails = FlightEndpointDetails.builder()
                .airport(anotherAirport)
                .flightAirportDetails(flightAirportDetails)
                .delayMinutes(DELAY_MINUTES)
                .scheduledTime(time)
                .estimatedTime(time)
                .actualTime(time)
                .estimatedRunwayTime(time)
                .actualRunwayTime(time)
                .build();
    }

    @Test
    void builderTest_airport() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .airport(airport)
                .build();

        assertEquals(airport, flightEndpointDetails.getAirport());
    }

    @Test
    void builderTest_flightAirportDetails() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .flightAirportDetails(flightAirportDetails)
                .build();

        assertEquals(flightAirportDetails, flightEndpointDetails.getFlightAirportDetails());
    }

    @Test
    void builderTest_delayMinutes() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .delayMinutes(DELAY_MINUTES)
                .build();

        assertEquals(DELAY_MINUTES, flightEndpointDetails.getDelayMinutes());
    }

    @Test
    void builderTest_scheduledTime() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .scheduledTime(time)
                .build();

        assertEquals(time, flightEndpointDetails.getScheduledTime());
    }

    @Test
    void builderTest_estimatedTime() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .estimatedTime(time)
                .build();

        assertEquals(time, flightEndpointDetails.getEstimatedTime());
    }

    @Test
    void builderTest_actualTime() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .actualTime(time)
                .build();

        assertEquals(time, flightEndpointDetails.getActualTime());
    }

    @Test
    void builderTest_estimatedRunwayTime() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .estimatedRunwayTime(time)
                .build();

        assertEquals(time, flightEndpointDetails.getEstimatedRunwayTime());
    }

    @Test
    void builderTest_actualRunwayTime() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .actualRunwayTime(time)
                .build();

        assertEquals(time, flightEndpointDetails.getActualRunwayTime());
    }

    @Test
    void builderTest_toBuilder() {
        FlightEndpointDetails flightEndpointDetails = FlightEndpointDetails.builder()
                .airport(airport)
                .scheduledTime(time)
                .build();

        FlightEndpointDetails modifiedFlightEndpointDetails = flightEndpointDetails.toBuilder()
                .flightAirportDetails(flightAirportDetails)
                .build();

        FlightEndpointDetails expectedFlightEndpointDetails = FlightEndpointDetails.builder()
                .airport(airport)
                .flightAirportDetails(flightAirportDetails)
                .scheduledTime(time)
                .build();

        assertEquals(expectedFlightEndpointDetails, modifiedFlightEndpointDetails);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(FlightEndpointDetails.class).verify();
    }
}