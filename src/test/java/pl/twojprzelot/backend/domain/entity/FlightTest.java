package pl.twojprzelot.backend.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private static final String ID = "ID";
    private static final String FLIGHT_IATA_NUMBER = "FLIGHT_IATA_NUMBER";
    private static final double LATITUDE = 2.0;
    private static final double LONGITUDE = 3.0;
    private static final double ALTITUDE = 4.0;
    private static final String AIRPORT_NAME = "AIRPORT_NAME";
    private static final String ANOTHER_AIRPORT_NAME = "ANOTHER_AIRPORT_NAME";
    private static final String AIRLINE_NAME = "AIRLINE_NAME";

    private Flight firstFlight;
    private Flight sameFlightAsFirstFlight;
    private Flight anotherFlight;

    private FlightIdentifier flightIdentifier;
    private GeographicPosition geographicPosition;
    private Airport departure;
    private Airport arrival;
    private Airline airline;

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

        departure = Airport.builder()
                .name(AIRPORT_NAME)
                .build();

        arrival = Airport.builder()
                .name(ANOTHER_AIRPORT_NAME)
                .build();

        airline = Airline.builder()
                .name(AIRLINE_NAME)
                .build();

        firstFlight = Flight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .geographicPosition(geographicPosition)
                .departure(departure)
                .arrival(arrival)
                .airline(airline)
                .build();

        sameFlightAsFirstFlight = Flight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .geographicPosition(geographicPosition)
                .departure(departure)
                .arrival(arrival)
                .airline(airline)
                .build();

        anotherFlight = Flight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .geographicPosition(geographicPosition)
                .departure(arrival)
                .arrival(arrival)
                .airline(airline)
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
    void builderTest_departure() {
        Flight flight = Flight.builder()
                .departure(departure)
                .build();

        assertEquals(departure, flight.getDeparture());
    }

    @Test
    void builderTest_arrival() {
        Flight flight = Flight.builder()
                .arrival(arrival)
                .build();

        assertEquals(arrival, flight.getArrival());
    }

    @Test
    void builderTest_airline() {
        Flight flight = Flight.builder()
                .airline(airline)
                .build();

        assertEquals(airline, flight.getAirline());
    }

    @Test
    void builderTest_toBuilder() {
        Flight flight = Flight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .airline(airline)
                .build();

        Flight modifiedFlight = flight.toBuilder()
                .departure(departure)
                .arrival(arrival)
                .build();

        Flight expectedFlight = Flight.builder()
                .id(ID)
                .flightIdentifier(flightIdentifier)
                .departure(departure)
                .arrival(arrival)
                .airline(airline)
                .build();

        assertEquals(expectedFlight, modifiedFlight);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstFlight, sameFlightAsFirstFlight);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstFlight, anotherFlight);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstFlight.hashCode(), sameFlightAsFirstFlight.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstFlight.hashCode(), anotherFlight.hashCode());
    }
}