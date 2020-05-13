package pl.twojprzelot.backend.adapter.repository.database;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.*;

@Mapper
public interface EntityMapper {

    ScheduledFlight mapFromScheduledFlightEntity(ScheduledFlightEntity scheduledFlightEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "from")
    ScheduledFlightEntity mapToScheduledFlightEntity(ScheduledFlight scheduledFlight);

    Country mapToCountry(CountryEntity countryEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "from")
    @Mapping(ignore = true, target = "nameTranslations")
    CountryEntity mapToCountryEntity(Country country);

    Currency mapToCurrency(CurrencyEntity currencyEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "from")
    CurrencyEntity mapToCurrencyEntity(Currency currency);

    City mapToCity(CityEntity cityEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "from")
    @Mapping(ignore = true, target = "nameTranslations")
    CityEntity mapToCityEntity(City city);

    Airline mapToAirline(AirlineEntity airlineEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "from")
    AirlineEntity mapToAirlineEntity(Airline airline);

    Airport mapToAirport(AirportEntity airportEntity);

    @InheritInverseConfiguration
    @Mapping(ignore = true, target = "from")
    @Mapping(ignore = true, target = "nameTranslations")
    AirportEntity mapToAirportEntity(Airport airport);
}
