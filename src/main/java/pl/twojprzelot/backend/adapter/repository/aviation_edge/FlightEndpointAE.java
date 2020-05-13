package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
final class FlightEndpointAE {
    private String iataCode;
    private String icaoCode;
    private String terminal;
    private String gate;

    @JsonProperty("delay")
    private int delayMinutes;

    private LocalDateTime scheduledTime;
    private LocalDateTime estimatedTime;
    private LocalDateTime actualTime;
    private LocalDateTime estimatedRunwayTime;
    private LocalDateTime actualRunwayTime;
}
