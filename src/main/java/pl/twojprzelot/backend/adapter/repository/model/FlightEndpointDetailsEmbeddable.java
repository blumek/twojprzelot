package pl.twojprzelot.backend.adapter.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
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
