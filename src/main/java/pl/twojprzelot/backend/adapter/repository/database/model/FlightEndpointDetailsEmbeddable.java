package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class FlightEndpointDetailsEmbeddable {
    @ManyToOne
    private AirportEntity airport;

    @Embedded
    private FlightAirportDetailsEmbeddable flightAirportDetails;

    private int delayMinutes;
    private LocalDateTime scheduledTime;
    private LocalDateTime estimatedTime;
    private LocalDateTime actualTime;
    private LocalDateTime estimatedRunwayTime;
    private LocalDateTime actualRunwayTime;
}
