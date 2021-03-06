package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "scheduled_flight")
final class ScheduledFlightEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Embedded
    private FlightIdentifierEmbeddable flightIdentifier;

    @Embedded
    @AssociationOverride(name = "airport", joinColumns = @JoinColumn(name = "departure_airport_id"))
    @AttributeOverride(name = "flightAirportDetails.gate", column = @Column(name = "departure_gate"))
    @AttributeOverride(name = "flightAirportDetails.terminal", column = @Column(name = "departure_terminal"))
    @AttributeOverride(name = "delayMinutes", column = @Column(name = "departure_delay_minutes"))
    @AttributeOverride(name = "scheduledTime", column = @Column(name = "departure_scheduled_time"))
    @AttributeOverride(name = "estimatedTime", column = @Column(name = "departure_estimated_time"))
    @AttributeOverride(name = "actualTime", column = @Column(name = "departure_actual_time"))
    @AttributeOverride(name = "estimatedRunwayTime", column = @Column(name = "departure_estimated_runway_time"))
    @AttributeOverride(name = "actualRunwayTime", column = @Column(name = "departure_actual_runway_time"))
    private FlightEndpointDetailsEmbeddable departure;

    @Embedded
    @AssociationOverride(name = "airport", joinColumns = @JoinColumn(name = "arrival_airport_id"))
    @AttributeOverride(name = "flightAirportDetails.gate", column = @Column(name = "arrival_gate"))
    @AttributeOverride(name = "flightAirportDetails.terminal", column = @Column(name = "arrival_terminal"))
    @AttributeOverride(name = "delayMinutes", column = @Column(name = "arrival_delay_minutes"))
    @AttributeOverride(name = "scheduledTime", column = @Column(name = "arrival_scheduled_time"))
    @AttributeOverride(name = "estimatedTime", column = @Column(name = "arrival_estimated_time"))
    @AttributeOverride(name = "actualTime", column = @Column(name = "arrival_actual_time"))
    @AttributeOverride(name = "estimatedRunwayTime", column = @Column(name = "arrival_estimated_runway_time"))
    @AttributeOverride(name = "actualRunwayTime", column = @Column(name = "arrival_actual_runway_time"))
    private FlightEndpointDetailsEmbeddable arrival;

    @ManyToOne
    private AirlineEntity airline;

    public static ScheduledFlightEntity from(ScheduledFlight scheduledFlight) {
        return mapper.mapToScheduledFlightEntity(scheduledFlight);
    }

    public ScheduledFlight toScheduledFlight() {
        return mapper.mapFromScheduledFlightEntity(this);
    }
}
