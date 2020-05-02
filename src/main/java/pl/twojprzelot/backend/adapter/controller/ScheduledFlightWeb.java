package pl.twojprzelot.backend.adapter.controller;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
class ScheduledFlightWeb {
    private static final WebMapper mapper = Mappers.getMapper(WebMapper.class);

    private int id;
    private FlightIdentifierWeb flightIdentifier;
    private FlightEndpointDetailsWeb departure;
    private FlightEndpointDetailsWeb arrival;
    private AirlineWeb airline;

    public static ScheduledFlightWeb from(ScheduledFlight scheduledFlight) {
        return mapper.mapToScheduledFlightWeb(scheduledFlight);
    }
}
