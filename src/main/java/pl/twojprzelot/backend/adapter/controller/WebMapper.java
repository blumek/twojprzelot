package pl.twojprzelot.backend.adapter.controller;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

@Mapper
interface WebMapper {
    @Mapping(source = "departure.airport.city.name", target = "departure.airport.cityName")
    @Mapping(source = "departure.airport.city.country.name", target = "departure.airport.countryName")
    @Mapping(source = "departure.flightAirportDetails.gate", target = "departure.gate")
    @Mapping(source = "departure.flightAirportDetails.terminal", target = "departure.terminal")
    @Mapping(source = "arrival.airport.city.name", target = "arrival.airport.cityName")
    @Mapping(source = "arrival.airport.city.country.name", target = "arrival.airport.countryName")
    @Mapping(source = "arrival.flightAirportDetails.gate", target = "arrival.gate")
    @Mapping(source = "arrival.flightAirportDetails.terminal", target = "arrival.terminal")
    ScheduledFlightWeb mapToScheduledFlightWeb(ScheduledFlight scheduledFlight);
}
