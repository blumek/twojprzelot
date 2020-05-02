package pl.twojprzelot.backend.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AirportTest {
    private static final int ID = 5;
    private static final String NAME = "NAME";
    private static final String ANOTHER_AIRPORT_NAME = "ANOTHER_AIRPORT_NAME";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final double LATITUDE = 1.0;
    private static final double LONGITUDE = 2.0;
    private static final String CITY_NAME = "CITY_NAME";

    private Airport firsAirport;
    private Airport sameAirportAsFirsAirport;
    private Airport anotherAirport;

    private GeographicLocation geographicLocation;
    private City city;

    @BeforeEach
    void setUp() {
        geographicLocation = GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        city = City.builder()
                .name(CITY_NAME)
                .build();

        firsAirport = Airport.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .city(city)
                .geographicLocation(geographicLocation)
                .build();

        sameAirportAsFirsAirport = Airport.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .city(city)
                .geographicLocation(geographicLocation)
                .build();

        anotherAirport = Airport.builder()
                .id(ID)
                .name(ANOTHER_AIRPORT_NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .city(city)
                .geographicLocation(geographicLocation)
                .build();
    }

    @Test
    void builderTest_id() {
        Airport airport = Airport.builder()
                .id(ID)
                .build();

        assertEquals(ID, airport.getId());
    }

    @Test
    void builderTest_name() {
        Airport airport = Airport.builder()
                .name(NAME)
                .build();

        assertEquals(NAME, airport.getName());
    }

    @Test
    void builderTest_iataCode() {
        Airport airport = Airport.builder()
                .iataCode(IATA_CODE)
                .build();

        assertEquals(IATA_CODE, airport.getIataCode());
    }

    @Test
    void builderTest_icaoCode() {
        Airport airport = Airport.builder()
                .icaoCode(ICAO_CODE)
                .build();

        assertEquals(ICAO_CODE, airport.getIcaoCode());
    }

    @Test
    void builderTest_geographicLocation() {
        Airport airport = Airport.builder()
                .geographicLocation(geographicLocation)
                .build();

        assertEquals(geographicLocation, airport.getGeographicLocation());
    }

    @Test
    void builderTest_city() {
        Airport airport = Airport.builder()
                .city(city)
                .build();

        assertEquals(city, airport.getCity());
    }

    @Test
    void builderTest_toBuilder() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .build();

        Airport modifiedAirport = airport.toBuilder()
                .icaoCode(ICAO_CODE)
                .build();

        Airport expectedAirport = Airport.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        assertEquals(expectedAirport, modifiedAirport);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firsAirport, sameAirportAsFirsAirport);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firsAirport, anotherAirport);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firsAirport.hashCode(), sameAirportAsFirsAirport.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firsAirport.hashCode(), anotherAirport.hashCode());
    }
}