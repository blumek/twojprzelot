package pl.twojprzelot.backend.adapter.controller.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledFlightWebTest {
    private static final int FLIGHT_ID_NUMBER = 500;
    private static final String FLIGHT_ID_IATA_NUMBER = "FLIGHT_ID_IATA_NUMBER";
    private static final String FLIGHT_ID_ICAO_NUMBER = "FLIGHT_ID_ICAO_NUMBER";
    private static final String COUNTRY_NAME = "COUNTRY_NAME";
    private static final String ANOTHER_COUNTRY_NAME = "ANOTHER_COUNTRY_NAME";
    private static final String CITY_NAME = "CITY_NAME";
    private static final String ANOTHER_CITY_NAME = "ANOTHER_CITY_NAME";
    private static final int AIRPORT_ID = 3;
    private static final String AIRPORT_NAME = "AIRPORT_NAME";
    private static final String AIRPORT_IATA_CODE = "AIRPORT_IATA_CODE";
    private static final String AIRPORT_ICAO_CODE = "AIRPORT_ICAO_CODE";
    private static final String GATE = "GATE";
    private static final String TERMINAL = "TERMINAL";
    private static final int DELAY_MINUTES = 10;
    private static final int YEAR = 2000;
    private static final int MONTH = 10;
    private static final int DAY_OF_MONTH = 1;
    private static final int HOUR = 10;
    private static final int MINUTE = 10;
    private static final int SECOND = 0;
    private static final int AIRLINE_ID = 2;
    private static final String AIRLINE_NAME = "AIRLINE_NAME";
    private static final String AIRLINE_IATA_CODE = "AIRLINE_IATA_CODE";
    private static final String AIRLINE_ICAO_CODE = "AIRLINE_ICAO_CODE";
    private static final String SCHEDULED_FLIGHT_ID = "SCHEDULED_FLIGHT_ID";

    private ScheduledFlight scheduledFlight;
    private ScheduledFlightWeb scheduledFlightWeb;

    private City city;
    private Airport airport;
    private FlightEndpointDetails departure;
    private FlightEndpointDetailsWeb departureWeb;
    private AirportWeb airportWeb;

    @BeforeEach
    void setUp() {
        FlightIdentifier flightIdentifier = FlightIdentifier.builder()
                .number(FLIGHT_ID_NUMBER)
                .iataNumber(FLIGHT_ID_IATA_NUMBER)
                .icaoNumber(FLIGHT_ID_ICAO_NUMBER)
                .build();

        Country country = Country.builder()
                .name(COUNTRY_NAME)
                .build();

        Country anotherCountry = country.toBuilder()
                .name(ANOTHER_COUNTRY_NAME)
                .build();

        city = City.builder()
                .name(CITY_NAME)
                .country(country)
                .build();

        City anotherCity = city.toBuilder()
                .name(ANOTHER_CITY_NAME)
                .country(anotherCountry)
                .build();

        airport = Airport.builder()
                .id(AIRPORT_ID)
                .name(AIRPORT_NAME)
                .iataCode(AIRPORT_IATA_CODE)
                .icaoCode(AIRPORT_ICAO_CODE)
                .city(city)
                .build();

        Airport anotherAirport = airport.toBuilder()
                .city(anotherCity)
                .build();

        FlightAirportDetails flightAirportDetails = FlightAirportDetails.builder()
                .gate(GATE)
                .terminal(TERMINAL)
                .build();

        LocalDateTime time = LocalDateTime.of(YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE, SECOND);

        departure = FlightEndpointDetails.builder()
                .airport(airport)
                .flightAirportDetails(flightAirportDetails)
                .delayMinutes(DELAY_MINUTES)
                .scheduledTime(time)
                .estimatedTime(time)
                .actualTime(time)
                .estimatedRunwayTime(time)
                .actualRunwayTime(time)
                .build();

        FlightEndpointDetails arrival = departure.toBuilder()
                .airport(anotherAirport)
                .build();

        Airline airline = Airline.builder()
                .id(AIRLINE_ID)
                .name(AIRLINE_NAME)
                .iataCode(AIRLINE_IATA_CODE)
                .icaoCode(AIRLINE_ICAO_CODE)
                .build();

        scheduledFlight = ScheduledFlight.builder()
                .id(SCHEDULED_FLIGHT_ID)
                .flightIdentifier(flightIdentifier)
                .departure(departure)
                .arrival(arrival)
                .airline(airline)
                .build();

        FlightIdentifierWeb flightIdentifierWeb = FlightIdentifierWeb.builder()
                .number(FLIGHT_ID_NUMBER)
                .iataNumber(FLIGHT_ID_IATA_NUMBER)
                .icaoNumber(FLIGHT_ID_ICAO_NUMBER)
                .build();

        airportWeb = AirportWeb.builder()
                .id(AIRPORT_ID)
                .iataCode(AIRPORT_IATA_CODE)
                .icaoCode(AIRPORT_ICAO_CODE)
                .name(AIRPORT_NAME)
                .cityName(CITY_NAME)
                .countryName(COUNTRY_NAME)
                .build();

        AirportWeb anotherAirportWeb = airportWeb.toBuilder()
                .cityName(ANOTHER_CITY_NAME)
                .countryName(ANOTHER_COUNTRY_NAME)
                .build();

        departureWeb = FlightEndpointDetailsWeb.builder()
                .airport(airportWeb)
                .gate(GATE)
                .terminal(TERMINAL)
                .delayMinutes(DELAY_MINUTES)
                .scheduledTime(time)
                .estimatedTime(time)
                .actualTime(time)
                .estimatedRunwayTime(time)
                .actualRunwayTime(time)
                .build();

        FlightEndpointDetailsWeb arrivalWeb = departureWeb.toBuilder()
                .airport(anotherAirportWeb)
                .build();

        AirlineWeb airlineWeb = AirlineWeb.builder()
                .id(AIRLINE_ID)
                .iataCode(AIRLINE_IATA_CODE)
                .icaoCode(AIRLINE_ICAO_CODE)
                .name(AIRLINE_NAME)
                .build();

        scheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(SCHEDULED_FLIGHT_ID)
                .flightIdentifier(flightIdentifierWeb)
                .departure(departureWeb)
                .arrival(arrivalWeb)
                .airline(airlineWeb)
                .build();
    }

    @Test
    void fromTest_fullObject() {
        assertEquals(scheduledFlightWeb, ScheduledFlightWeb.from(scheduledFlight));
    }

    @Test
    void fromTest_countryNotAvailable() {
        removeCountryFromScheduledFlight();
        removeCountryNameFromScheduledFlightWeb();

        ScheduledFlightWeb from = ScheduledFlightWeb.from(scheduledFlight);
        assertEquals(scheduledFlightWeb, from);
    }

    private void removeCountryFromScheduledFlight() {
        city = city.toBuilder()
                .country(null)
                .build();

        airport = airport.toBuilder()
                .city(city)
                .build();

        departure = departure.toBuilder()
                .airport(airport)
                .build();

        scheduledFlight = scheduledFlight.toBuilder()
                .departure(departure)
                .build();
    }

    private void removeCountryNameFromScheduledFlightWeb() {
        airportWeb = airportWeb.toBuilder()
                .countryName(null)
                .build();

        departureWeb = departureWeb.toBuilder()
                .airport(airportWeb)
                .build();

        scheduledFlightWeb = scheduledFlightWeb.toBuilder()
                .departure(departureWeb)
                .build();
    }

    @Test
    void fromTest_cityNotAvailable() {
        removeCityFromScheduledFlight();
        removeCityNameFromScheduledFlightWeb();

        ScheduledFlightWeb from = ScheduledFlightWeb.from(scheduledFlight);
        assertEquals(scheduledFlightWeb, from);
    }

    private void removeCityFromScheduledFlight() {
        airport = airport.toBuilder()
                .city(null)
                .build();

        departure = departure.toBuilder()
                .airport(airport)
                .build();

        scheduledFlight = scheduledFlight.toBuilder()
                .departure(departure)
                .build();
    }

    private void removeCityNameFromScheduledFlightWeb() {
        airportWeb = airportWeb.toBuilder()
                .cityName(null)
                .countryName(null)
                .build();

        departureWeb = departureWeb.toBuilder()
                .airport(airportWeb)
                .build();

        scheduledFlightWeb = scheduledFlightWeb.toBuilder()
                .departure(departureWeb)
                .build();
    }

    @Test
    void fromTest_flightAirportDetailsNotAvailable() {
        removeFlightAirportDetailsFromScheduledFlight();
        removeGateAndTerminalFromScheduledFlightWeb();

        ScheduledFlightWeb from = ScheduledFlightWeb.from(scheduledFlight);
        assertEquals(scheduledFlightWeb, from);
    }

    private void removeFlightAirportDetailsFromScheduledFlight() {
        departure = departure.toBuilder()
                .flightAirportDetails(null)
                .build();

        scheduledFlight = scheduledFlight.toBuilder()
                .departure(departure)
                .build();
    }

    private void removeGateAndTerminalFromScheduledFlightWeb() {
        departureWeb = departureWeb.toBuilder()
                .gate(null)
                .terminal(null)
                .build();

        scheduledFlightWeb = scheduledFlightWeb.toBuilder()
                .departure(departureWeb)
                .build();
    }
}