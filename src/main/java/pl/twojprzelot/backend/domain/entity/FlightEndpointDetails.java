package pl.twojprzelot.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@Builder(toBuilder = true)
@Value
public class FlightEndpointDetails {
    Airport airport;
    FlightAirportDetails flightAirportDetails;
    int delayMinutes;
    LocalDateTime scheduledTime;
    LocalDateTime estimatedTime;
    LocalDateTime actualTime;
    LocalDateTime estimatedRunwayTime;
    LocalDateTime actualRunwayTime;
}
