package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.adapter.mapper.DomainMapper;
import pl.twojprzelot.backend.domain.entity.*;

@Mapper(uses = DomainMapper.class)
interface AviationEdgeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "airline.id", ignore = true)
    @Mapping(source = "departure.iataCode", target = "departure.airport.iataCode")
    @Mapping(source = "departure.icaoCode", target = "departure.airport.icaoCode")
    @Mapping(source = "departure.terminal", target = "departure.flightAirportDetails.terminal")
    @Mapping(source = "departure.gate", target = "departure.flightAirportDetails.gate")
    @Mapping(source = "arrival.iataCode", target = "arrival.airport.iataCode")
    @Mapping(source = "arrival.icaoCode", target = "arrival.airport.icaoCode")
    @Mapping(source = "arrival.terminal", target = "arrival.flightAirportDetails.terminal")
    @Mapping(source = "arrival.gate", target = "arrival.flightAirportDetails.gate")
    ScheduledFlight mapToScheduledFlight(ScheduledFlightAE scheduledFlightAE);

    @Mapping(source = "latitude", target = "geographicLocation.latitude")
    @Mapping(source = "longitude", target = "geographicLocation.longitude")
    @Mapping(source = "cityIataCode", target = "city.iataCode")
    @Mapping(source = "countryName", target = "city.country.name")
    @Mapping(source = "countryIso2Code", target = "city.country.iso2Code")
    Airport mapToAirport(AirportAE airportAE);

    @Mapping(source = "countryIso2Code", target = "country.iso2Code")
    @Mapping(source = "latitude", target = "geographicLocation.latitude")
    @Mapping(source = "longitude", target = "geographicLocation.longitude")
    City mapToCity(CityAE cityAE);

    @Mapping(source = "currencyCode", target = "currency.code")
    Country mapToCountry(CountryAE countryAE);

    Airline mapToAirline(AirlineAE airlineAE);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "airplaneSpeed.horizontal", target = "airplaneSpeed.horizontalSpeed")
    @Mapping(source = "airplaneSpeed.vertical", target = "airplaneSpeed.verticalSpeed")
    Flight mapToFlight(FlightAE flightAE);
}
