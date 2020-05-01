package pl.twojprzelot.backend.adapter.repository.aviation_edge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@ToString
class FlightEndpoint {
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
