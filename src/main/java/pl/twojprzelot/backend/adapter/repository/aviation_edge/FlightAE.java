package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Flight;

@NoArgsConstructor
@Setter
@Getter
@ToString
class FlightAE {
    private static final AviationEdgeMapper mapper = Mappers.getMapper(AviationEdgeMapper.class);

    @JsonProperty("flight")
    private FlightIdentifierAE flightIdentifier;

    private AirlineShortAE airline;
    private AirportShortAE departure;
    private AirportShortAE arrival;
    private AircraftShortAE aircraft;

    @JsonProperty("geography")
    private GeographicPositionAE geographicPosition;

    @JsonProperty("speed")
    private AirplaneSpeedAE airplaneSpeed;

    private String status;
    private System system;

    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    static class System {
        private String squawk;
        private long updated;
    }

    public Flight toFlight() {
        return mapper.mapToFlight(this);
    }
}
