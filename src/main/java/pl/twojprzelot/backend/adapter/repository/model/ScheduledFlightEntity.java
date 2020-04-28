package pl.twojprzelot.backend.adapter.repository.model;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.adapter.mapper.ScheduledFlightMapper;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity(name = "scheduled_flight")
public class ScheduledFlightEntity extends BaseEntity {
    private static final ScheduledFlightMapper mapper = Mappers.getMapper(ScheduledFlightMapper.class);

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
    FlightEndpointDetailsEmbeddable departure;

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
    FlightEndpointDetailsEmbeddable arrival;

    @ManyToOne
    AirlineEntity airline;

    public static ScheduledFlightEntity from(ScheduledFlight scheduledFlight) {
        return mapper.mapToScheduledFlightEntity(scheduledFlight);
    }

    public ScheduledFlight toScheduledFlight() {
        return mapper.mapFromScheduledFlightEntity(this);
    }
}
