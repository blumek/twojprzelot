package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.City;

@Mapper
interface CityAEMapper {
    @Mapping(source = "countryIso2Code", target = "country.iso2Code")
    @Mapping(source = "latitude", target = "geographicLocation.latitude")
    @Mapping(source = "longitude", target = "geographicLocation.longitude")
    City mapToCity(CityAE cityAE);
}
