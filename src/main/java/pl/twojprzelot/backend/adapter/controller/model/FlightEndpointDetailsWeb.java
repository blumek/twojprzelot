package pl.twojprzelot.backend.adapter.controller.model;

import lombok.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public class FlightEndpointDetailsWeb {
    private AirportWeb airport;
    private String gate;
    private String terminal;
    private int delayMinutes;
    private LocalDateTime scheduledTime;
    private LocalDateTime estimatedTime;
    private LocalDateTime actualTime;
    private LocalDateTime estimatedRunwayTime;
    private LocalDateTime actualRunwayTime;
}
