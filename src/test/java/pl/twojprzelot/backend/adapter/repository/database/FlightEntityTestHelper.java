package pl.twojprzelot.backend.adapter.repository.database;

import pl.twojprzelot.backend.domain.entity.AirplaneSpeed;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;
import pl.twojprzelot.backend.domain.entity.GeographicPosition;

class FlightEntityTestHelper {
    private static final int ID = 1;
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";
    private static final String NUMBER = "NUMBER";
    private static final int LATITUDE = 2;
    private static final int LONGITUDE = 3;
    private static final int ALTITUDE = 4;
    private static final int DIRECTION = 5;
    private static final int HORIZONTAL_SPEED = 10;
    private static final int VERTICAL_SPEED = 20;

    FlightEntity flightEntity;
    Flight flight;
    
    void init() {
        FlightIdentifierEmbeddable flightIdentifier = new FlightIdentifierEmbeddable();
        flightIdentifier.setIataNumber(IATA_NUMBER);
        flightIdentifier.setIcaoNumber(ICAO_NUMBER);
        flightIdentifier.setNumber(NUMBER);

        GeographicPositionEmbeddable geographicPosition = new GeographicPositionEmbeddable();
        geographicPosition.setLatitude(LATITUDE);
        geographicPosition.setLongitude(LONGITUDE);
        geographicPosition.setAltitude(ALTITUDE);
        geographicPosition.setDirection(DIRECTION);

        AirplaneSpeedEmbeddable airplaneSpeed = new AirplaneSpeedEmbeddable();
        airplaneSpeed.setHorizontalSpeed(HORIZONTAL_SPEED);
        airplaneSpeed.setVerticalSpeed(VERTICAL_SPEED);
        
        flightEntity = new FlightEntity();
        flightEntity.setId(ID);
        flightEntity.setFlightIdentifier(flightIdentifier);
        flightEntity.setGeographicPosition(geographicPosition);
        flightEntity.setAirplaneSpeed(airplaneSpeed);
                
        flight = Flight.builder()
                .id(ID)
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .icaoNumber(ICAO_NUMBER)
                        .number(NUMBER)
                        .build())
                .geographicPosition(GeographicPosition.builder()
                        .latitude(LATITUDE)
                        .longitude(LONGITUDE)
                        .altitude(ALTITUDE)
                        .direction(DIRECTION)
                        .build())
                .airplaneSpeed(AirplaneSpeed.builder()
                        .horizontalSpeed(HORIZONTAL_SPEED)
                        .verticalSpeed(VERTICAL_SPEED)
                        .build())
                .build();
    }

    void removeIdEntity() {
        flightEntity.setId(0);
    }

    void removeId() {
        flight = flight.toBuilder()
                .id(0)
                .build();
    }

    void removeFlightIdentifierEntity() {
        flightEntity.setFlightIdentifier(null);
    }

    void removeFlightIdentifier() {
        flight = flight.toBuilder()
                .flightIdentifier(null)
                .build();
    }

    void removeGeographicPositionEntity() {
        flightEntity.setGeographicPosition(null);
    }

    void removeGeographicPosition() {
        flight = flight.toBuilder()
                .geographicPosition(null)
                .build();
    }

    void removeAirplaneSpeedEntity() {
        flightEntity.setAirplaneSpeed(null);
    }

    void removeAirplaneSpeed() {
        flight = flight.toBuilder()
                .airplaneSpeed(null)
                .build();
    }
}
