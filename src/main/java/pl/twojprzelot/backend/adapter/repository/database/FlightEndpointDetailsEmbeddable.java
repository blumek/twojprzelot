package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Embeddable
final class FlightEndpointDetailsEmbeddable {
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
