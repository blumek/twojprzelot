package pl.twojprzelot.backend.adapter.repository.aviation_edge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
class Flight {
    @JsonProperty("flight")
    private FlightIdentifier flightIdentifier;

    private AirlineShort airline;
    private AirportShort departure;
    private AirportShort arrival;
    private AircraftShort aircraft;

    @JsonProperty("geography")
    private GeographicPosition geographicPosition;

    @JsonProperty("speed")
    private AirplaneSpeed airplaneSpeed;

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
}
