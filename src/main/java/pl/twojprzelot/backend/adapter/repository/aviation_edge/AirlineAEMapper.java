package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.mapstruct.Mapper;
import pl.twojprzelot.backend.domain.entity.Airline;

@Mapper
interface AirlineAEMapper {
    Airline mapToAirline(AirlineAE airlineAE);
}
