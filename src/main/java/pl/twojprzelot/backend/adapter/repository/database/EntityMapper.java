package pl.twojprzelot.backend.adapter.repository.database;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.*;

@Mapper
public interface EntityMapper {

    ScheduledFlight mapFromScheduledFlightEntity(ScheduledFlightEntity scheduledFlightEntity);

    @InheritInverseConfiguration
    ScheduledFlightEntity mapToScheduledFlightEntity(ScheduledFlight scheduledFlight);

    Flight mapFromFlightEntity(FlightEntity flightEntity);

    @InheritInverseConfiguration
    FlightEntity mapToFlightEntity(Flight flight);

    Country mapToCountry(CountryEntity countryEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "nameTranslations")
    @Mapping(ignore = true, target = "cities")
    CountryEntity mapToCountryEntity(Country country);

    Currency mapToCurrency(CurrencyEntity currencyEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "countries")
    CurrencyEntity mapToCurrencyEntity(Currency currency);

    City mapToCity(CityEntity cityEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "nameTranslations")
    @Mapping(ignore = true, target = "airports")
    CityEntity mapToCityEntity(City city);

    Airline mapToAirline(AirlineEntity airlineEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "scheduledFlights")
    AirlineEntity mapToAirlineEntity(Airline airline);

    Airport mapToAirport(AirportEntity airportEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "nameTranslations")
    @Mapping(ignore = true, target = "arrivalScheduledFlights")
    @Mapping(ignore = true, target = "departureScheduledFlights")
    AirportEntity mapToAirportEntity(Airport airport);
}
