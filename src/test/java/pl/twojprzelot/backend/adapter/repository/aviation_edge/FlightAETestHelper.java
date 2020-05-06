package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import pl.twojprzelot.backend.domain.entity.*;

class FlightAETestHelper {
    static final String FLIGHT_ID_NUMBER_TEXT = "100";
    static final int FLIGHT_ID_NUMBER = 100;
    static final String FLIGHT_ID_IATA_NUMBER = "FLIGHT_ID_IATA_NUMBER";
    static final String FLIGHT_ID_ICAO_NUMBER = "FLIGHT_ID_ICAO_NUMBER";
    static final String AIRLINE_NAME = "AIRLINE_NAME";
    static final String AIRLINE_IATA_NAME = "AIRLINE_IATA_NAME";
    static final String AIRLINE_ICAO_NAME = "AIRLINE_ICAO_NAME";
    static final double LATITUDE = 10.0;
    static final double LONGITUDE = 20.0;
    static final double ALTITUDE = 30.0;
    static final double DIRECTION = 40.0;
    static final String DEPARTURE_AIRPORT_IATA_CODE = "DEPARTURE_AIRPORT_IATA_CODE";
    static final String DEPARTURE_AIRPORT_ICAO_CODE = "DEPARTURE_AIRPORT_ICAO_CODE";
    static final String ARRIVAL_AIRPORT_IATA_CODE = "ARRIVAL_AIRPORT_IATA_CODE";
    static final String ARRIVAL_AIRPORT_ICAO_CODE = "ARRIVAL_AIRPORT_ICAO_CODE";

    FlightAE flightAE;
    Flight flight;

    void init() {
        FlightIdentifierAE flightIdentifierAE = new FlightIdentifierAE();
        flightIdentifierAE.setNumber(FLIGHT_ID_NUMBER_TEXT);
        flightIdentifierAE.setIataNumber(FLIGHT_ID_IATA_NUMBER);
        flightIdentifierAE.setIcaoNumber(FLIGHT_ID_ICAO_NUMBER);

        AirlineShortAE airlineAE = new AirlineShortAE();
        airlineAE.setName(AIRLINE_NAME);
        airlineAE.setIataCode(AIRLINE_IATA_NAME);
        airlineAE.setIcaoCode(AIRLINE_ICAO_NAME);

        GeographicPositionAE geographicPositionAE = new GeographicPositionAE();
        geographicPositionAE.setLatitude(LATITUDE);
        geographicPositionAE.setLongitude(LONGITUDE);
        geographicPositionAE.setAltitude(ALTITUDE);
        geographicPositionAE.setDirection(DIRECTION);

        AirportShortAE departureAE = new AirportShortAE();
        departureAE.setIataCode(DEPARTURE_AIRPORT_IATA_CODE);
        departureAE.setIcaoCode(DEPARTURE_AIRPORT_ICAO_CODE);

        AirportShortAE arrivalAE = new AirportShortAE();
        arrivalAE.setIataCode(ARRIVAL_AIRPORT_IATA_CODE);
        arrivalAE.setIcaoCode(ARRIVAL_AIRPORT_ICAO_CODE);

        flightAE = new FlightAE();
        flightAE.setFlightIdentifier(flightIdentifierAE);
        flightAE.setAirline(airlineAE);
        flightAE.setGeographicPosition(geographicPositionAE);
        flightAE.setDeparture(departureAE);
        flightAE.setArrival(arrivalAE);

        flight = Flight.builder()
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
                .geographicPosition(GeographicPosition.builder()
                        .latitude(LATITUDE)
                        .longitude(LONGITUDE)
                        .altitude(ALTITUDE)
                        .direction(DIRECTION)
                        .build())
                .departure(Airport.builder()
                        .iataCode(DEPARTURE_AIRPORT_IATA_CODE)
                        .icaoCode(DEPARTURE_AIRPORT_ICAO_CODE)
                        .build())
                .arrival(Airport.builder()
                        .iataCode(ARRIVAL_AIRPORT_IATA_CODE)
                        .icaoCode(ARRIVAL_AIRPORT_ICAO_CODE)
                        .build())
                .build();
    }

    void removeAirlineAE() {
        flightAE.setAirline(null);
    }

    void removeAirline() {
        flight = flight.toBuilder()
                .airline(null)
                .build();
    }

    void removeFlightIdentifierAE() {
        flightAE.setFlightIdentifier(null);
    }

    void removeFlightIdentifier() {
        flight = flight.toBuilder()
                .flightIdentifier(null)
                .build();
    }

    void removeDepartureAE() {
        flightAE.setDeparture(null);
    }

    void removeDeparture() {
        flight = flight.toBuilder()
                .departure(null)
                .build();
    }

    void removeDepartureAEData() {
        flightAE.getDeparture().setIataCode(null);
        flightAE.getDeparture().setIcaoCode(null);
    }

    void removeArrivalAE() {
        flightAE.setArrival(null);
    }

    void removeArrival() {
        flight = flight.toBuilder()
                .arrival(null)
                .build();
    }

    void removeArrivalAEData() {
        flightAE.getArrival().setIataCode(null);
        flightAE.getArrival().setIcaoCode(null);
    }
}