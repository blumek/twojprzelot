package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.twojprzelot.backend.domain.entity.Country;

@Mapper
interface CountryAEMapper {
    @Mapping(ignore = true, target = "currency")
    Country mapToCountry(CountryAE countryAE);
}
