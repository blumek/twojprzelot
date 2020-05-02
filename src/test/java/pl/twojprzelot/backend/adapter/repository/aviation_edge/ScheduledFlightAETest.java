package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledFlightAETest {
    private static final String FLIGHT_ID_NUMBER_TEXT = "100";
    private static final int FLIGHT_ID_NUMBER = 100;
    private static final String FLIGHT_ID_IATA_NUMBER = "FLIGHT_ID_IATA_NUMBER";
    private static final String FLIGHT_ID_ICAO_NUMBER = "FLIGHT_ID_ICAO_NUMBER";
    private static final String AIRLINE_NAME = "AIRLINE_NAME";
    private static final String AIRLINE_IATA_NAME = "AIRLINE_IATA_NAME";
    private static final String AIRLINE_ICAO_NAME = "AIRLINE_ICAO_NAME";
    private static final String DEPARTURE_AIRPORT_IATA_CODE = "DEPARTURE_AIRPORT_IATA_CODE";
    private static final String DEPARTURE_AIRPORT_ICAO_CODE = "DEPARTURE_AIRPORT_ICAO_CODE";
    private static final String DEPARTURE_TERMINAL = "DEPARTURE_TERMINAL";
    private static final String DEPARTURE_GATE = "DEPARTURE_GATE";
    private static final String ARRIVAL_AIRPORT_IATA_CODE = "ARRIVAL_AIRPORT_IATA_CODE";
    private static final String ARRIVAL_AIRPORT_ICAO_CODE = "ARRIVAL_AIRPORT_ICAO_CODE";
    private static final String ARRIVAL_TERMINAL = "ARRIVAL_TERMINAL";
    private static final String ARRIVAL_GATE = "ARRIVAL_GATE";
    private static final int DEPARTURE_DELAY_MINUTES = 25;
    private static final int ARRIVAL_DELAY_MINUTES = 15;

    private ScheduledFlightAE scheduledFlightAE;
    private ScheduledFlight scheduledFlight;

    @BeforeEach
    void setUp() {
        FlightIdentifierAE flightIdentifierAE = new FlightIdentifierAE();
        flightIdentifierAE.setNumber(FLIGHT_ID_NUMBER_TEXT);
        flightIdentifierAE.setIataNumber(FLIGHT_ID_IATA_NUMBER);
        flightIdentifierAE.setIcaoNumber(FLIGHT_ID_ICAO_NUMBER);

        AirlineShortAE airlineAE = new AirlineShortAE();
        airlineAE.setName(AIRLINE_NAME);
        airlineAE.setIataCode(AIRLINE_IATA_NAME);
        airlineAE.setIcaoCode(AIRLINE_ICAO_NAME);

        LocalDateTime departureTime = LocalDateTime.of(2000, 10, 10, 10, 10, 0);

        FlightEndpointAE departureAE = new FlightEndpointAE();
        departureAE.setIataCode(DEPARTURE_AIRPORT_IATA_CODE);
        departureAE.setIcaoCode(DEPARTURE_AIRPORT_ICAO_CODE);
        departureAE.setTerminal(DEPARTURE_TERMINAL);
        departureAE.setGate(DEPARTURE_GATE);
        departureAE.setDelayMinutes(DEPARTURE_DELAY_MINUTES);
        departureAE.setScheduledTime(departureTime);
        departureAE.setEstimatedTime(departureTime);
        departureAE.setActualTime(departureTime);
        departureAE.setEstimatedRunwayTime(departureTime);
        departureAE.setActualRunwayTime(departureTime);

        LocalDateTime arrivalTime = LocalDateTime.of(2010, 12, 15, 10, 10, 0);

        FlightEndpointAE arrivalAE = new FlightEndpointAE();
        arrivalAE.setIataCode(ARRIVAL_AIRPORT_IATA_CODE);
        arrivalAE.setIcaoCode(ARRIVAL_AIRPORT_ICAO_CODE);
        arrivalAE.setTerminal(ARRIVAL_TERMINAL);
        arrivalAE.setGate(ARRIVAL_GATE);
        arrivalAE.setDelayMinutes(ARRIVAL_DELAY_MINUTES);
        arrivalAE.setScheduledTime(arrivalTime);
        arrivalAE.setEstimatedTime(arrivalTime);
        arrivalAE.setActualTime(arrivalTime);
        arrivalAE.setEstimatedRunwayTime(arrivalTime);
        arrivalAE.setActualRunwayTime(arrivalTime);

        scheduledFlightAE = new ScheduledFlightAE();
        scheduledFlightAE.setFlightIdentifier(flightIdentifierAE);
        scheduledFlightAE.setAirline(airlineAE);
        scheduledFlightAE.setDeparture(departureAE);
        scheduledFlightAE.setArrival(arrivalAE);

        scheduledFlight = ScheduledFlight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .number(FLIGHT_ID_NUMBER)
                        .iataNumber(FLIGHT_ID_IATA_NUMBER)
                        .icaoNumber(FLIGHT_ID_ICAO_NUMBER)
                        .build())
                .airline(Airline.builder()
                        .name(AIRLINE_NAME)
                        .iataCode(AIRLINE_IATA_NAME)
                        .icaoCode(AIRLINE_ICAO_NAME)
                        .build())
                .departure(FlightEndpointDetails.builder()
                        .airport(Airport.builder()
                                .iataCode(DEPARTURE_AIRPORT_IATA_CODE)
                                .icaoCode(DEPARTURE_AIRPORT_ICAO_CODE)
                                .build())
                        .flightAirportDetails(FlightAirportDetails.builder()
                                .terminal(DEPARTURE_TERMINAL)
                                .gate(DEPARTURE_GATE)
                                .build())
                        .delayMinutes(DEPARTURE_DELAY_MINUTES)
                        .scheduledTime(departureTime)
                        .estimatedTime(departureTime)
                        .actualTime(departureTime)
                        .estimatedRunwayTime(departureTime)
                        .actualRunwayTime(departureTime)
                        .build())
                .arrival(FlightEndpointDetails.builder()
                        .airport(Airport.builder()
                                .iataCode(ARRIVAL_AIRPORT_IATA_CODE)
                                .icaoCode(ARRIVAL_AIRPORT_ICAO_CODE)
                                .build())
                        .flightAirportDetails(FlightAirportDetails.builder()
                                .terminal(ARRIVAL_TERMINAL)
                                .gate(ARRIVAL_GATE)
                                .build())
                        .delayMinutes(ARRIVAL_DELAY_MINUTES)
                        .scheduledTime(arrivalTime)
                        .estimatedTime(arrivalTime)
                        .actualTime(arrivalTime)
                        .estimatedRunwayTime(arrivalTime)
                        .actualRunwayTime(arrivalTime)
                        .build())
                .build();
    }

    @Test
    void toScheduledFlightTest() {
        assertEquals(scheduledFlight, scheduledFlightAE.toScheduledFlight());
    }
}