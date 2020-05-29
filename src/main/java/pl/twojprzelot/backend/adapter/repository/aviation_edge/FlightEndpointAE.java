package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
final class FlightEndpointAE {
    private String iataCode;
    private String icaoCode;
    private String terminal;
    private String gate;
    private String baggage;

    @JsonProperty("delay")
    private int delayMinutes;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime scheduledTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime estimatedTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime actualTime;

    @JsonProperty("estimatedRunway")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime estimatedRunwayTime;

    @JsonProperty("actualRunway")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime actualRunwayTime;
}
