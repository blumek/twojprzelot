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
class ScheduledFlightAE {
    @JsonProperty("flight")
    private FlightIdentifierAE flightIdentifier;

    private AirlineShortAE airline;
    private FlightEndpointAE departure;
    private FlightEndpointAE arrival;
    private String status;
    private String type;
}
