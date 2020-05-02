package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.GeographicLocation;

import static org.junit.jupiter.api.Assertions.*;

class AirportAETest {
    private static final int ID = 1;
    private static final String NAME = "NAME";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final double LATITUDE = 10.5;
    private static final double LONGITUDE = 12.5;
    private static final String CITY_IATA_CODE = "CITY_IATA_CODE";
    private static final String COUNTRY_NAME = "COUNTRY_NAME";
    private static final String COUNTRY_ISO_2_CODE = "COUNTRY_ISO_2_CODE";
    private AirportAE airportAE;
    private Airport airport;

    @BeforeEach
    void setUp() {
        airportAE = new AirportAE();
        airportAE.setId(ID);
        airportAE.setName(NAME);
        airportAE.setIataCode(IATA_CODE);
        airportAE.setIcaoCode(ICAO_CODE);
        airportAE.setLatitude(LATITUDE);
        airportAE.setLongitude(LONGITUDE);
        airportAE.setCityIataCode(CITY_IATA_CODE);
        airportAE.setCountryName(COUNTRY_NAME);
        airportAE.setCountryIso2Code(COUNTRY_ISO_2_CODE);

        airport = Airport.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .geographicLocation(GeographicLocation.builder()
                        .latitude(LATITUDE)
                        .longitude(LONGITUDE)
                        .build())
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .country(Country.builder()
                                .name(COUNTRY_NAME)
                                .iso2Code(COUNTRY_ISO_2_CODE)
                                .build())
                        .build())
                .build();
    }

    @Test
    void toAirportTest_fullObject() {
        assertEquals(airport, airportAE.toAirport());
    }
}