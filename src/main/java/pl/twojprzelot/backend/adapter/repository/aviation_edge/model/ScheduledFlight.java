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
class ScheduledFlight {
    @JsonProperty("flight")
    private FlightIdentifier flightIdentifier;

    private AirlineShort airline;
    private FlightEndpoint departure;
    private FlightEndpoint arrival;
    private String status;
    private String type;
}
