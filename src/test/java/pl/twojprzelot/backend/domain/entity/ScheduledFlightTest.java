package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledFlightTest {
    private static final int ID = 1;
    private static final String FLIGHT_IATA_NUMBER = "FLIGHT_IATA_NUMBER";
    private static final int FIRST_DELAY_MINUTES = 10;
    private static final int SECOND_DELAY_MINUTES = 20;
    private static final String AIRLINE_NAME = "AIRLINE_NAME";
    private static final String ANOTHER_AIRLINE_NAME = "ANOTHER_AIRLINE_NAME";

    private ScheduledFlight firstScheduledFlight;
    private ScheduledFlight sameScheduledFlightFirstScheduledFlight;
    private ScheduledFlight anotherScheduledFlight;

    private FlightIdentifier flightIdentifier;
    private FlightEndpointDetails departure;
    private FlightEndpointDetails arrival;
    private Airline airline;

    @BeforeEach
    void setUp() {
        flightIdentifier = FlightIdentifier.builder()
                .iataNumber(FLIGHT_IATA_NUMBER)
                .build();

        departure = FlightEndpointDetails.builder()
                .delayMinutes(FIRST_DELAY_MINUTES)
                .build();

        arrival = FlightEndpointDetails.builder()
                .delayMinutes(SECOND_DELAY_MINUTES)
                .build();

        airline = Airline.builder()
                .name(AIRLINE_NAME)
                .build();

        Airline anotherAirline = Airline.builder()
                .name(ANOTHER_AIRLINE_NAME)
                .build();

        firstScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .departure(departure)
                .arrival(arrival)
                .airline(airline)
                .build();

        sameScheduledFlightFirstScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .departure(departure)
                .arrival(arrival)
                .airline(airline)
                .build();

        anotherScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .departure(departure)
                .arrival(arrival)
                .airline(anotherAirline)
                .build();
    }

    @Test
    void builderTest_id() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        assertEquals(ID, scheduledFlight.getId());
    }

    @Test
    void builderTest_flightIdentifier() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .flightIdentifier(flightIdentifier)
                .build();

        assertEquals(flightIdentifier, scheduledFlight.getFlightIdentifier());
    }

    @Test
    void builderTest_departure() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .departure(departure)
                .build();

        assertEquals(departure, scheduledFlight.getDeparture());
    }

    @Test
    void builderTest_arrival() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .arrival(arrival)
                .build();

        assertEquals(arrival, scheduledFlight.getArrival());
    }

    @Test
    void builderTest_airline() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .airline(airline)
                .build();

        assertEquals(airline, scheduledFlight.getAirline());
    }

    @Test
    void builderTest_toBuilder() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .departure(departure)
                .arrival(arrival)
                .build();

        ScheduledFlight modifiedScheduledFlight = scheduledFlight.toBuilder()
                .airline(airline)
                .build();

        ScheduledFlight expectedScheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .departure(departure)
                .arrival(arrival)
                .airline(airline)
                .build();

        assertEquals(expectedScheduledFlight, modifiedScheduledFlight);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(ScheduledFlight.class).verify();
    }
}