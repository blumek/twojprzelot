package pl.twojprzelot.backend.adapter.repository.database;

import pl.twojprzelot.backend.domain.entity.*;

import java.time.LocalDateTime;

class ScheduledFlightEntityTestHelper {
    static final String DEPARTURE = "DEPARTURE";
    static final String ARRIVAL = "ARRIVAL";
    static final int SCHEDULED_FLIGHT_ID = 6;
    static final String FLIGHT_ID_NUMBER = "500";
    static final String FLIGHT_ID_IATA_NUMBER = "FLIGHT_ID_IATA_NUMBER";
    static final String FLIGHT_ID_ICAO_NUMBER = "FLIGHT_ID_ICAO_NUMBER";
    static final int DELAY_MINUTES = 5;
    static final int AIRPORT_ID = 3;
    static final String AIRPORT_IATA_CODE = "_AIRPORT_IATA_CODE";
    static final String AIRPORT_ICAO_CODE = "_AIRPORT_ICAO_CODE";
    static final int CITY_ID = 2;
    static final String CITY_IATA_CODE = "_CITY_IATA_CODE";
    static final String GATE = "_GATE";
    static final String TERMINAL = "_TERMINAL";
    static final double LATITUDE = 15.5;
    static final double LONGITUDE = 40.5;
    static final int COUNTRY_ID = 1;
    static final int COUNTRY_ISO_NUMBER = 700;
    static final String COUNTRY_ISO_2_CODE = "_COUNTRY_ISO_2_CODE";
    static final String COUNTRY_ISO_3_CODE = "_COUNTRY_ISO_3_CODE";
    static final int COUNTRY_POPULATION = 50000;
    static final int CURRENCY_ID = 4;
    static final int CURRENCY_ISO_NUMBER = 900;
    static final String CURRENCY_CODE = "_CURRENCY_CODE";
    static final int AIRLINE_ID = 2;
    static final String AIRLINE_NAME = "AIRLINE_NAME";
    static final String AIRLINE_IATA_CODE = "AIRLINE_IATA_CODE";
    static final String AIRLINE_ICAO_CODE = "AIRLINE_ICAO_CODE";

    ScheduledFlightEntity scheduledFlightEntity;
    ScheduledFlight scheduledFlight;

    LocalDateTime time;

    void init() {
        time = LocalDateTime.of(2000, 10, 10, 10 , 10, 0);

        scheduledFlight = createScheduledFlight();
        scheduledFlightEntity = createScheduledFlightEntity();
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
                .id(AIRPORT_ID)
                .iataCode(name + AIRPORT_IATA_CODE)
                .icaoCode(name + AIRPORT_ICAO_CODE)
                .name(name)
                .city(createCity(name))
                .geographicLocation(createGeographicLocation())
                .build();
    }

    public City createCity(String name) {
        return City.builder()
                .id(CITY_ID)
                .iataCode(name + CITY_IATA_CODE)
                .name(name)
                .country(createCountry(name))
                .geographicLocation(createGeographicLocation())
                .build();
    }

    public Country createCountry(String name) {
        return Country.builder()
                .id(COUNTRY_ID)
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
                .id(CURRENCY_ID)
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
        airportEntity.setId(AIRPORT_ID);
        airportEntity.setName(name);
        airportEntity.setIataCode(name + AIRPORT_IATA_CODE);
        airportEntity.setIcaoCode(name + AIRPORT_ICAO_CODE);
        airportEntity.setCity(createCityEntity(name));
        airportEntity.setGeographicLocation(createGeographicLocationEmbeddable());
        return airportEntity;
    }

    private CityEntity createCityEntity(String name) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(CITY_ID);
        cityEntity.setName(name);
        cityEntity.setIataCode(name + CITY_IATA_CODE);
        cityEntity.setCountry(createCountryEntity(name));
        cityEntity.setGeographicLocation(createGeographicLocationEmbeddable());
        return cityEntity;
    }

    private CountryEntity createCountryEntity(String name) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(COUNTRY_ID);
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
        currencyEntity.setId(CURRENCY_ID);
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

    void removeFlightIdentifierEmbeddable() {
        scheduledFlightEntity.setFlightIdentifier(null);
    }

    void removeFlightIdentifier() {
        scheduledFlight = scheduledFlight.toBuilder()
                .flightIdentifier(null)
                .build();
    }

    void removeFlightEndpointDetailsEmbeddableAirport(FlightEndpointDetailsEmbeddable flightEndpointDetailsEmbeddable) {
        flightEndpointDetailsEmbeddable.setAirport(null);
    }

    FlightEndpointDetails removeFlightEndpointDetailsAirport(FlightEndpointDetails flightEndpointDetails) {
        return flightEndpointDetails.toBuilder()
                .airport(null)
                .build();
    }

    void removeFlightEndpointDetailsEmbeddableAirportCity(FlightEndpointDetailsEmbeddable flightEndpointDetailsEmbeddable) {
        flightEndpointDetailsEmbeddable.getAirport()
                .setCity(null);
    }

    FlightEndpointDetails removeFlightEndpointDetailsAirportCity(FlightEndpointDetails flightEndpointDetails) {
         return flightEndpointDetails.toBuilder()
                .airport(flightEndpointDetails.getAirport().toBuilder()
                        .city(null)
                        .build())
                .build();
    }

    void removeFlightEndpointDetailsEmbeddableAirportGeographicLocation(FlightEndpointDetailsEmbeddable
                                                                                flightEndpointDetailsEmbeddable) {
        flightEndpointDetailsEmbeddable.getAirport()
                .setGeographicLocation(null);
    }

    FlightEndpointDetails removeFlightEndpointDetailsAirportGeographicLocation(FlightEndpointDetails
                                                                                           flightEndpointDetails) {
        return flightEndpointDetails.toBuilder()
                .airport(flightEndpointDetails.getAirport().toBuilder()
                        .geographicLocation(null)
                        .build())
                .build();
    }

    void removeFlightEndpointDetailsEmbeddableAirportCityCountry(FlightEndpointDetailsEmbeddable
                                                                                flightEndpointDetailsEmbeddable) {
        flightEndpointDetailsEmbeddable.getAirport()
                .getCity()
                .setCountry(null);
    }

    FlightEndpointDetails removeFlightEndpointDetailsAirportCityCountry(FlightEndpointDetails
                                                                                       flightEndpointDetails) {
        return flightEndpointDetails.toBuilder()
                .airport(flightEndpointDetails.getAirport().toBuilder()
                        .city(flightEndpointDetails.getAirport().getCity().toBuilder()
                                .country(null)
                                .build())
                        .build())
                .build();
    }

    void removeFlightEndpointDetailsEmbeddableAirportCityGeographicLocation(FlightEndpointDetailsEmbeddable
                                                                         flightEndpointDetailsEmbeddable) {
        flightEndpointDetailsEmbeddable.getAirport()
                .getCity()
                .setGeographicLocation(null);
    }

    FlightEndpointDetails removeFlightEndpointDetailsAirportCityGeographicLocation(FlightEndpointDetails
                                                                                flightEndpointDetails) {
        return flightEndpointDetails.toBuilder()
                .airport(flightEndpointDetails.getAirport().toBuilder()
                        .city(flightEndpointDetails.getAirport().getCity().toBuilder()
                                .geographicLocation(null)
                                .build())
                        .build())
                .build();
    }

    void removeFlightEndpointDetailsEmbeddableAirportCityCountryCurrency(FlightEndpointDetailsEmbeddable
                                                                         flightEndpointDetailsEmbeddable) {
        flightEndpointDetailsEmbeddable.getAirport()
                .getCity()
                .getCountry()
                .setCurrency(null);
    }

    FlightEndpointDetails removeFlightEndpointDetailsAirportCityCountryCurrency(FlightEndpointDetails
                                                                                flightEndpointDetails) {
        return flightEndpointDetails.toBuilder()
                .airport(flightEndpointDetails.getAirport().toBuilder()
                        .city(flightEndpointDetails.getAirport().getCity().toBuilder()
                                .country(flightEndpointDetails.getAirport().getCity().getCountry().toBuilder()
                                        .currency(null)
                                        .build())
                                .build())
                        .build())
                .build();
    }

    void removeFlightEndpointDetailsEmbeddableFlightAirportDetails(FlightEndpointDetailsEmbeddable
                                                                           flightEndpointDetailsEmbeddable) {
        flightEndpointDetailsEmbeddable.setFlightAirportDetails(null);
    }

    FlightEndpointDetails removeFlightEndpointDetailsFlightAirportDetails(FlightEndpointDetails flightEndpointDetails) {
        return flightEndpointDetails.toBuilder()
                .flightAirportDetails(null)
                .build();
    }

    void removeDepartureEntity() {
        scheduledFlightEntity.setDeparture(null);
    }

    void removeDeparture() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(null)
                .build();
    }

    void removeDepartureEntityAirport() {
        removeFlightEndpointDetailsEmbeddableAirport(scheduledFlightEntity.getDeparture());
    }

    void removeDepartureAirport() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(removeFlightEndpointDetailsAirport(scheduledFlight.getDeparture()))
                .build();
    }

    void removeDepartureEntityAirportCity() {
        removeFlightEndpointDetailsEmbeddableAirportCity(scheduledFlightEntity.getDeparture());
    }

    void removeDepartureAirportCity() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(removeFlightEndpointDetailsAirportCity(scheduledFlight.getDeparture()))
                .build();
    }

    void removeDepartureEntityAirportGeographicLocation() {
        removeFlightEndpointDetailsEmbeddableAirportGeographicLocation(scheduledFlightEntity.getDeparture());
    }

    void removeDepartureAirportGeographicLocation() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(removeFlightEndpointDetailsAirportGeographicLocation(scheduledFlight.getDeparture()))
                .build();
    }

    void removeDepartureEntityAirportCityCountry() {
        removeFlightEndpointDetailsEmbeddableAirportCityCountry(scheduledFlightEntity.getDeparture());
    }

    void removeDepartureAirportCityCountry() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(removeFlightEndpointDetailsAirportCityCountry(scheduledFlight.getDeparture()))
                .build();
    }

    void removeDepartureEntityAirportCityGeographicLocation() {
        removeFlightEndpointDetailsEmbeddableAirportCityGeographicLocation(scheduledFlightEntity.getDeparture());
    }

    void removeDepartureAirportCityGeographicLocation() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(removeFlightEndpointDetailsAirportCityGeographicLocation(scheduledFlight.getDeparture()))
                .build();
    }

    void removeDepartureEntityAirportCityCountryCurrency() {
        removeFlightEndpointDetailsEmbeddableAirportCityCountryCurrency(scheduledFlightEntity.getDeparture());
    }

    void removeDepartureAirportCityCountryCurrency() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(removeFlightEndpointDetailsAirportCityCountryCurrency(scheduledFlight.getDeparture()))
                .build();
    }

    void removeDepartureEntityFlightAirportDetails() {
        removeFlightEndpointDetailsEmbeddableFlightAirportDetails(scheduledFlightEntity.getDeparture());
    }

    void removeDepartureFlightAirportDetails() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(removeFlightEndpointDetailsFlightAirportDetails(scheduledFlight.getDeparture()))
                .build();
    }

    void removeArrivalEntity() {
        scheduledFlightEntity.setArrival(null);
    }

    void removeArrival() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(null)
                .build();
    }

    void removeArrivalEntityAirport() {
        removeFlightEndpointDetailsEmbeddableAirport(scheduledFlightEntity.getArrival());
    }

    void removeArrivalAirport() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(removeFlightEndpointDetailsAirport(scheduledFlight.getArrival()))
                .build();
    }

    void removeArrivalEntityAirportCity() {
        removeFlightEndpointDetailsEmbeddableAirportCity(scheduledFlightEntity.getArrival());
    }

    void removeArrivalAirportCity() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(removeFlightEndpointDetailsAirportCity(scheduledFlight.getArrival()))
                .build();
    }

    void removeArrivalEntityAirportGeographicLocation() {
        removeFlightEndpointDetailsEmbeddableAirportGeographicLocation(scheduledFlightEntity.getArrival());
    }

    void removeArrivalAirportGeographicLocation() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(removeFlightEndpointDetailsAirportGeographicLocation(scheduledFlight.getArrival()))
                .build();
    }

    void removeArrivalEntityAirportCityCountry() {
        removeFlightEndpointDetailsEmbeddableAirportCityCountry(scheduledFlightEntity.getArrival());
    }

    void removeArrivalAirportCityCountry() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(removeFlightEndpointDetailsAirportCityCountry(scheduledFlight.getArrival()))
                .build();
    }

    void removeArrivalEntityAirportCityGeographicLocation() {
        removeFlightEndpointDetailsEmbeddableAirportCityGeographicLocation(scheduledFlightEntity.getArrival());
    }

    void removeArrivalAirportCityGeographicLocation() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(removeFlightEndpointDetailsAirportCityGeographicLocation(scheduledFlight.getArrival()))
                .build();
    }

    void removeArrivalEntityAirportCityCountryCurrency() {
        removeFlightEndpointDetailsEmbeddableAirportCityCountryCurrency(scheduledFlightEntity.getArrival());
    }

    void removeArrivalAirportCityCountryCurrency() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(removeFlightEndpointDetailsAirportCityCountryCurrency(scheduledFlight.getArrival()))
                .build();
    }

    void removeArrivalEntityFlightAirportDetails() {
        removeFlightEndpointDetailsEmbeddableFlightAirportDetails(scheduledFlightEntity.getArrival());
    }

    void removeArrivalFlightAirportDetails() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(removeFlightEndpointDetailsFlightAirportDetails(scheduledFlight.getArrival()))
                .build();
    }

    void removeAirlineEntity() {
        scheduledFlightEntity.setAirline(null);
    }

    void removeAirline() {
        scheduledFlight = scheduledFlight.toBuilder()
                .airline(null)
                .build();
    }
}