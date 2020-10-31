package pl.twojprzelot.backend.adapter.controller;

import lombok.*;
import org.mapstruct.factory.Mappers;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
final class FlightInformationWeb {
    private static final WebMapper mapper = Mappers.getMapper(WebMapper.class);

    private FlightWeb flight;
    private ScheduledFlightWeb scheduledFlight;

    public static FlightInformationWeb from(FlightWeb flight, ScheduledFlightWeb scheduledFlight) {
        return mapper.mapToFlightInformationWeb(flight, scheduledFlight);
    }

    public static FlightInformationWeb from(FlightWeb flight) {
        return mapper.mapToFlightInformationWeb(flight);
    }

    public static FlightInformationWeb from(ScheduledFlightWeb scheduledFlight) {
        return mapper.mapToFlightInformationWeb(scheduledFlight);
    }
}
