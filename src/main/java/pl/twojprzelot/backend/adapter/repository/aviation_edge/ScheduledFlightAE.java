package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"codeshared"})
final class ScheduledFlightAE {
    private static final AviationEdgeMapper mapper = Mappers.getMapper(AviationEdgeMapper.class);

    @JsonProperty("flight")
    private FlightIdentifierAE flightIdentifier;

    private AirlineShortAE airline;
    private FlightEndpointAE departure;
    private FlightEndpointAE arrival;
    private String status;
    private String type;

    public ScheduledFlight toScheduledFlight() {
        return mapper.mapToScheduledFlight(this);
    }
}
