package pl.twojprzelot.backend.adapter.controller;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Flight;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
final class FlightWeb {
    private static final WebMapper mapper = Mappers.getMapper(WebMapper.class);

    private int id;
    private FlightIdentifierWeb flightIdentifier;
    private GeographicPositionWeb geographicPosition;
    private AirplaneSpeedWeb airplaneSpeed;

    public static FlightWeb from(Flight flight) {
        return mapper.mapToFlightWeb(flight);
    }
}
