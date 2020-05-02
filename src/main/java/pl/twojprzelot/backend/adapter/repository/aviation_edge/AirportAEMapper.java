package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.Airport;

@Mapper
public interface AirportAEMapper {
    @Mapping(source = "latitude", target = "geographicLocation.latitude")
    @Mapping(source = "longitude", target = "geographicLocation.longitude")
    @Mapping(source = "cityIataCode", target = "city.iataCode")
    @Mapping(source = "countryName", target = "city.country.name")
    @Mapping(source = "countryIso2Code", target = "city.country.iso2Code")
    Airport mapToAirport(AirportAE airportAE);
}
