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
    AirportEntity airport;

    @Embedded
    private FlightAirportDetailsEmbeddable flightAirportDetails;

    int delayMinutes;
    LocalDateTime scheduledTime;
    LocalDateTime estimatedTime;
    LocalDateTime actualTime;
    LocalDateTime estimatedRunwayTime;
    LocalDateTime actualRunwayTime;
}
