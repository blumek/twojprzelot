package pl.twojprzelot.backend.adapter.repository.database.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduledFlightEntityTest {
    private static final String DEPARTURE = "DEPARTURE";
    private static final String ARRIVAL = "ARRIVAL";
    private static final String SCHEDULED_FLIGHT_ID = "SCHEDULED_FLIGHT_ID";
    private static final int FLIGHT_ID_NUMBER = 500;
    private static final String FLIGHT_ID_IATA_NUMBER = "FLIGHT_ID_IATA_NUMBER";
    private static final String FLIGHT_ID_ICAO_NUMBER = "FLIGHT_ID_ICAO_NUMBER";
    private static final int DELAY_MINUTES = 5;
    private static final String AIRPORT_ID = "_AIRPORT_ID";
    private static final String AIRPORT_IATA_CODE = "_AIRPORT_IATA_CODE";
    private static final String AIRPORT_ICAO_CODE = "_AIRPORT_ICAO_CODE";
    private static final String CITY_ID = "_CITY_ID";
    private static final String CITY_IATA_CODE = "_CITY_IATA_CODE";
    private static final String GATE = "_GATE";
    private static final String TERMINAL = "_TERMINAL";
    private static final double LATITUDE = 15.5;
    private static final double LONGITUDE = 40.5;
    private static final String COUNTRY_ID = "_COUNTRY_ID";
    private static final int COUNTRY_ISO_NUMBER = 700;
    private static final String COUNTRY_ISO_2_CODE = "_COUNTRY_ISO_2_CODE";
    private static final String COUNTRY_ISO_3_CODE = "_COUNTRY_ISO_3_CODE";
    private static final int COUNTRY_POPULATION = 50000;
    private static final String CURRENCY_ID = "_CURRENCY_ID";
    private static final int CURRENCY_ISO_NUMBER = 900;
    private static final String CURRENCY_CODE = "_CURRENCY_CODE";
    private static final String AIRLINE_ID = "AIRLINE_ID";
    private static final String AIRLINE_NAME = "AIRLINE_NAME";
    private static final String AIRLINE_IATA_CODE = "AIRLINE_IATA_CODE";
    private static final String AIRLINE_ICAO_CODE = "AIRLINE_ICAO_CODE";

    private LocalDateTime time;

    @BeforeEach
    void setUp() {
        time = LocalDateTime.of(2000, 10, 10, 10 , 10, 0);
    }

    @Test
    void fromTest_fullObjects() {
        ScheduledFlight scheduledFlight = createScheduledFlight();
        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutFlightIdentifier() {
        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .flightIdentifier(null)
                .build();

        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setFlightIdentifier(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDeparture() {
        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(null)
                .build();

        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setDeparture(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrival() {
        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(null)
                .build();

        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setArrival(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutAirline() {
        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .airline(null)
                .build();

        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setAirline(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_fullObjects() {
        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        ScheduledFlight scheduledFlight = createScheduledFlight();
        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutFlightIdentifier() {
        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setFlightIdentifier(null);

        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .flightIdentifier(null)
                .build();


        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_withoutDeparture() {
        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setDeparture(null);

        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(null)
                .build();


        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_withoutArrival() {
        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setArrival(null);

        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(null)
                .build();


        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_withoutAirline() {
        ScheduledFlightEntity scheduledFlightEntity = createScheduledFlightEntity();
        scheduledFlightEntity.setAirline(null);

        ScheduledFlight scheduledFlight = createScheduledFlight();
        scheduledFlight = scheduledFlight.toBuilder()
                .airline(null)
                .build();


        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    public ScheduledFlight createScheduledFlight() {
        return ScheduledFlight.builder()
                .id(SCHEDULED_FLIGHT_ID)
                .flightIdentifier(createFlightIdentifier())
                .departure(createFlightEndpointDetails(DEPARTURE))
                .arrival(createFlightEndpointDetails(ARRIVAL))
                .airline(createAirline())
                .build();
    }

    public FlightIdentifier createFlightIdentifier() {
        return FlightIdentifier.builder()
                .number(FLIGHT_ID_NUMBER)
                .iataNumber(FLIGHT_ID_IATA_NUMBER)
                .icaoNumber(FLIGHT_ID_ICAO_NUMBER)
                .build();
    }

    public FlightEndpointDetails createFlightEndpointDetails(String name) {
        return FlightEndpointDetails.builder()
                .airport(createAirport(name))
                .flightAirportDetails(createFlightAirportDetails(name))
                .delayMinutes(DELAY_MINUTES)
                .scheduledTime(time)
                .estimatedTime(time)
                .actualTime(time)
                .estimatedRunwayTime(time)
                .actualRunwayTime(time)
                .build();
    }

    public Airport createAirport(String name) {
        return Airport.builder()
                .id(name + AIRPORT_ID)
                .iataCode(name + AIRPORT_IATA_CODE)
                .icaoCode(name + AIRPORT_ICAO_CODE)
                .name(name)
                .city(createCity(name))
                .geographicLocation(createGeographicLocation())
                .build();
    }

    public City createCity(String name) {
        return City.builder()
                .id(name + CITY_ID)
                .iataCode(name + CITY_IATA_CODE)
                .name(name)
                .country(createCountry(name))
                .geographicLocation(createGeographicLocation())
                .build();
    }

    public Country createCountry(String name) {
        return Country.builder()
                .id(name + COUNTRY_ID)
                .isoNumber(COUNTRY_ISO_NUMBER)
                .iso2Code(name + COUNTRY_ISO_2_CODE)
                .iso3Code(name + COUNTRY_ISO_3_CODE)
                .name(name)
                .population(COUNTRY_POPULATION)
                .currency(createCurrency(name))
                .build();
    }

    public Currency createCurrency(String name) {
        return Currency.builder()
                .id(name + CURRENCY_ID)
                .name(name)
                .isoNumber(CURRENCY_ISO_NUMBER)
                .code(name + CURRENCY_CODE)
                .build();
    }

    public GeographicLocation createGeographicLocation() {
        return GeographicLocation.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();
    }

    public FlightAirportDetails createFlightAirportDetails(String name) {
        return FlightAirportDetails.builder()
                .gate(name + GATE)
                .terminal(name + TERMINAL)
                .build();
    }

    public Airline createAirline() {
        return Airline.builder()
                .id(AIRLINE_ID)
                .name(AIRLINE_NAME)
                .iataCode(AIRLINE_IATA_CODE)
                .icaoCode(AIRLINE_ICAO_CODE)
                .build();
    }

    public ScheduledFlightEntity createScheduledFlightEntity() {
        ScheduledFlightEntity scheduledFlightEntity = new ScheduledFlightEntity();
        scheduledFlightEntity.setId(SCHEDULED_FLIGHT_ID);
        scheduledFlightEntity.setFlightIdentifier(createFlightIdentifierEmbeddable());
        scheduledFlightEntity.setDeparture(createFlightEndpointDetailsEmbeddable(DEPARTURE));
        scheduledFlightEntity.setArrival(createFlightEndpointDetailsEmbeddable(ARRIVAL));
        scheduledFlightEntity.setAirline(createAirlineEntity());
        return scheduledFlightEntity;
    }

    private FlightIdentifierEmbeddable createFlightIdentifierEmbeddable() {
        FlightIdentifierEmbeddable flightIdentifierEmbeddable = new FlightIdentifierEmbeddable();
        flightIdentifierEmbeddable.setNumber(FLIGHT_ID_NUMBER);
        flightIdentifierEmbeddable.setIataNumber(FLIGHT_ID_IATA_NUMBER);
        flightIdentifierEmbeddable.setIcaoNumber(FLIGHT_ID_ICAO_NUMBER);
        return flightIdentifierEmbeddable;
    }

    private FlightEndpointDetailsEmbeddable createFlightEndpointDetailsEmbeddable(String name) {
        FlightEndpointDetailsEmbeddable flightEndpointDetailsEmbeddable = new FlightEndpointDetailsEmbeddable();
        flightEndpointDetailsEmbeddable.setAirport(createAirportEntity(name));
        flightEndpointDetailsEmbeddable.setFlightAirportDetails(createFlightAirportDetailsEmbeddable(name));
        flightEndpointDetailsEmbeddable.setDelayMinutes(DELAY_MINUTES);
        flightEndpointDetailsEmbeddable.setScheduledTime(time);
        flightEndpointDetailsEmbeddable.setEstimatedTime(time);
        flightEndpointDetailsEmbeddable.setActualTime(time);
        flightEndpointDetailsEmbeddable.setEstimatedRunwayTime(time);
        flightEndpointDetailsEmbeddable.setActualRunwayTime(time);
        return flightEndpointDetailsEmbeddable;
    }

    private AirportEntity createAirportEntity(String name) {
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setId(name + AIRPORT_ID);
        airportEntity.setName(name);
        airportEntity.setIataCode(name + AIRPORT_IATA_CODE);
        airportEntity.setIcaoCode(name + AIRPORT_ICAO_CODE);
        airportEntity.setCity(createCityEntity(name));
        airportEntity.setGeographicLocation(createGeographicLocationEmbeddable());
        return airportEntity;
    }

    private CityEntity createCityEntity(String name) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(name + CITY_ID);
        cityEntity.setName(name);
        cityEntity.setIataCode(name + CITY_IATA_CODE);
        cityEntity.setCountry(createCountryEntity(name));
        cityEntity.setGeographicLocation(createGeographicLocationEmbeddable());
        return cityEntity;
    }

    private CountryEntity createCountryEntity(String name) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(name + COUNTRY_ID);
        countryEntity.setIsoNumber(COUNTRY_ISO_NUMBER);
        countryEntity.setIso2Code(name + COUNTRY_ISO_2_CODE);
        countryEntity.setIso3Code(name + COUNTRY_ISO_3_CODE);
        countryEntity.setName(name);
        countryEntity.setPopulation(COUNTRY_POPULATION);
        countryEntity.setCurrency(createCurrencyEntity(name));
        return countryEntity;
    }

    private CurrencyEntity createCurrencyEntity(String name) {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(name + CURRENCY_ID);
        currencyEntity.setIsoNumber(CURRENCY_ISO_NUMBER);
        currencyEntity.setCode(name + CURRENCY_CODE);
        currencyEntity.setName(name);
        return currencyEntity;
    }

    private GeographicLocationEmbeddable createGeographicLocationEmbeddable() {
        GeographicLocationEmbeddable geographicLocationEmbeddable = new GeographicLocationEmbeddable();
        geographicLocationEmbeddable.setLatitude(LATITUDE);
        geographicLocationEmbeddable.setLongitude(LONGITUDE);
        return geographicLocationEmbeddable;
    }

    private FlightAirportDetailsEmbeddable createFlightAirportDetailsEmbeddable(String name) {
        FlightAirportDetailsEmbeddable flightAirportDetailsEmbeddable = new FlightAirportDetailsEmbeddable();
        flightAirportDetailsEmbeddable.setGate(name + GATE);
        flightAirportDetailsEmbeddable.setTerminal(name + TERMINAL);
        return flightAirportDetailsEmbeddable;
    }

    private AirlineEntity createAirlineEntity() {
        AirlineEntity airlineEntity = new AirlineEntity();
        airlineEntity.setId(AIRLINE_ID);
        airlineEntity.setIataCode(AIRLINE_IATA_CODE);
        airlineEntity.setIcaoCode(AIRLINE_ICAO_CODE);
        airlineEntity.setName(AIRLINE_NAME);
        return airlineEntity;
    }
}