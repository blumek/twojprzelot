package pl.twojprzelot.backend.adapter.controller;

import pl.twojprzelot.backend.domain.entity.*;

import java.time.LocalDateTime;

class ScheduledFlightWebTestHelper {
    static final String FLIGHT_ID_NUMBER = "500";
    static final String FLIGHT_ID_IATA_NUMBER = "FLIGHT_ID_IATA_NUMBER";
    static final String FLIGHT_ID_ICAO_NUMBER = "FLIGHT_ID_ICAO_NUMBER";
    static final String COUNTRY_NAME = "COUNTRY_NAME";
    static final String ANOTHER_COUNTRY_NAME = "ANOTHER_COUNTRY_NAME";
    static final String CITY_NAME = "CITY_NAME";
    static final String ANOTHER_CITY_NAME = "ANOTHER_CITY_NAME";
    static final int AIRPORT_ID = 3;
    static final String AIRPORT_NAME = "AIRPORT_NAME";
    static final String AIRPORT_IATA_CODE = "AIRPORT_IATA_CODE";
    static final String AIRPORT_ICAO_CODE = "AIRPORT_ICAO_CODE";
    static final String GATE = "GATE";
    static final String TERMINAL = "TERMINAL";
    static final int DELAY_MINUTES = 10;
    static final int YEAR = 2000;
    static final int MONTH = 10;
    static final int DAY_OF_MONTH = 1;
    static final int HOUR = 10;
    static final int MINUTE = 10;
    static final int SECOND = 0;
    static final int AIRLINE_ID = 2;
    static final String AIRLINE_NAME = "AIRLINE_NAME";
    static final String AIRLINE_IATA_CODE = "AIRLINE_IATA_CODE";
    static final String AIRLINE_ICAO_CODE = "AIRLINE_ICAO_CODE";
    static final int SCHEDULED_FLIGHT_ID = 4;

    ScheduledFlight scheduledFlight;
    ScheduledFlightWeb scheduledFlightWeb;

    City city;
    Airport airport;
    FlightEndpointDetails departure;
    FlightEndpointDetailsWeb departureWeb;
    AirportWeb airportWeb;

    void init() {
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

    void removeIdWeb() {
        scheduledFlightWeb = scheduledFlightWeb.toBuilder()
                .id(0)
                .build();
    }

    void removeId() {
        scheduledFlight = scheduledFlight.toBuilder()
                .id(0)
                .build();
    }

    void removeCountryFromScheduledFlight() {
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

     void removeCountryNameFromScheduledFlightWeb() {
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

    void removeCityFromScheduledFlight() {
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

    void removeCityNameFromScheduledFlightWeb() {
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

    void removeFlightAirportDetailsFromScheduledFlight() {
        departure = departure.toBuilder()
                .flightAirportDetails(null)
                .build();

        scheduledFlight = scheduledFlight.toBuilder()
                .departure(departure)
                .build();
    }

    void removeGateAndTerminalFromScheduledFlightWeb() {
        departureWeb = departureWeb.toBuilder()
                .gate(null)
                .terminal(null)
                .build();

        scheduledFlightWeb = scheduledFlightWeb.toBuilder()
                .departure(departureWeb)
                .build();
    }
}
