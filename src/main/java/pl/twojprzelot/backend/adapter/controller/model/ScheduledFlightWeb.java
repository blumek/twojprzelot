package pl.twojprzelot.backend.adapter.controller.model;

import lombok.*;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
public class ScheduledFlightWeb {
    private String id;
    private FlightIdentifierWeb flightIdentifier;
    private FlightEndpointDetailsWeb departure;
    private FlightEndpointDetailsWeb arrival;
    private AirlineWeb airlineWeb;
}
