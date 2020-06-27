package pl.twojprzelot.backend.adapter.controller;

import pl.twojprzelot.backend.domain.entity.AirplaneSpeed;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;
import pl.twojprzelot.backend.domain.entity.GeographicPosition;

class FlightWebTestHelper {
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";
    private static final String NUMBER = "123";
    private static final int LATITUDE = 1;
    private static final int ALTITUDE = 2;
    private static final int LONGITUDE = 3;
    private static final int DIRECTION = 4;
    private static final int HORIZONTAL_SPEED = 100;
    private static final int VERTICAL_SPEED = 200;

    Flight flight;
    FlightWeb flightWeb;

    void init() {
        flight = Flight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .icaoNumber(ICAO_NUMBER)
                        .number(NUMBER)
                        .build())
                .geographicPosition(GeographicPosition.builder()
                        .latitude(LATITUDE)
                        .altitude(ALTITUDE)
                        .longitude(LONGITUDE)
                        .direction(DIRECTION)
                        .build())
                .airplaneSpeed(AirplaneSpeed.builder()
                        .horizontalSpeed(HORIZONTAL_SPEED)
                        .verticalSpeed(VERTICAL_SPEED)
                        .build())
                .build();

        flightWeb = FlightWeb.builder()
                .flightIdentifier(FlightIdentifierWeb.builder()
                        .iataNumber(IATA_NUMBER)
                        .icaoNumber(ICAO_NUMBER)
                        .number(NUMBER)
                        .build())
                .geographicPosition(GeographicPositionWeb.builder()
                        .latitude(LATITUDE)
                        .altitude(ALTITUDE)
                        .longitude(LONGITUDE)
                        .direction(DIRECTION)
                        .build())
                .airplaneSpeed(AirplaneSpeedWeb.builder()
                        .horizontalSpeed(HORIZONTAL_SPEED)
                        .verticalSpeed(VERTICAL_SPEED)
                        .build())
                .build();
    }

    void removeFlightIdentifierWeb() {
        flightWeb = flightWeb.toBuilder()
                .flightIdentifier(null)
                .build();
    }

    void removeFlightIdentifier() {
        flight = flight.toBuilder()
                .flightIdentifier(null)
                .build();
    }

    void removeGeographicPositionWeb() {
        flightWeb = flightWeb.toBuilder()
                .geographicPosition(null)
                .build();
    }

    void removeGeographicPosition() {
        flight = flight.toBuilder()
                .geographicPosition(null)
                .build();
    }

    void removeAirplaneSpeedWeb() {
        flightWeb = flightWeb.toBuilder()
                .airplaneSpeed(null)
                .build();
    }

    void removeAirplaneSpeed() {
        flight = flight.toBuilder()
                .airplaneSpeed(null)
                .build();
    }
}