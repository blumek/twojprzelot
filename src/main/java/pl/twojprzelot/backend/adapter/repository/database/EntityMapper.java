package pl.twojprzelot.backend.adapter.repository.database;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

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

    Currency mapToCurrency(CurrencyEntity currencyEntity);

    City mapToCity(CityEntity cityEntity);
}
