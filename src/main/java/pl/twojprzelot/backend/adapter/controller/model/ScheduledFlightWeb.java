package pl.twojprzelot.backend.adapter.controller.model;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.adapter.mapper.ScheduledFlightMapper;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public class ScheduledFlightWeb {
    private static final ScheduledFlightMapper mapper = Mappers.getMapper(ScheduledFlightMapper.class);

    private String id;
    private FlightIdentifierWeb flightIdentifier;
    private FlightEndpointDetailsWeb departure;
    private FlightEndpointDetailsWeb arrival;
    private AirlineWeb airline;

    public static ScheduledFlightWeb from(ScheduledFlight scheduledFlight) {
        return mapper.mapToScheduledFlightWeb(scheduledFlight);
    }
}
