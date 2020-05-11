package pl.twojprzelot.backend.adapter.repository.database;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.*;

@Mapper
public interface EntityMapper {
    @Mapping(ignore = true, target = "from")
    @Mapping(ignore = true, target = "departure.airport.nameTranslations")
    @Mapping(ignore = true, target = "departure.airport.city.nameTranslations")
    @Mapping(ignore = true, target = "departure.airport.city.country.nameTranslations")
    @Mapping(ignore = true, target = "arrival.airport.nameTranslations")
    @Mapping(ignore = true, target = "arrival.airport.city.nameTranslations")
    @Mapping(ignore = true, target = "arrival.airport.city.country.nameTranslations")
    ScheduledFlightEntity mapToScheduledFlightEntity(ScheduledFlight scheduledFlight);

    @InheritInverseConfiguration(name = "mapToScheduledFlightEntity")
    ScheduledFlight mapFromScheduledFlightEntity(ScheduledFlightEntity scheduledFlightEntity);

    Country mapToCountry(CountryEntity countryEntity);

    @InheritInverseConfiguration
    CountryEntity mapToCountryEntity(Country country);

    Currency mapToCurrency(CurrencyEntity currencyEntity);

    @InheritInverseConfiguration
    CurrencyEntity mapToCurrencyEntity(Currency currency);

    City mapToCity(CityEntity cityEntity);

    @InheritInverseConfiguration
    CityEntity mapToCityEntity(City city);

    Airline mapToAirline(AirlineEntity airlineEntity);

    @InheritInverseConfiguration
    AirlineEntity mapToAirlineEntity(Airline airline);

    Airport mapToAirport(AirportEntity airportEntity);
}
