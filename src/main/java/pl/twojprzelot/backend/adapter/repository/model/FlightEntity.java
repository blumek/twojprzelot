package pl.twojprzelot.backend.adapter.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "flight")
public class FlightEntity extends BaseEntity {
    @Embedded
    private FlightIdentifierEmbeddable flightIdentifier;

    @Embedded
    private GeographicPositionEmbeddable geographicPosition;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private AirportEntity departure;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private AirportEntity arrival;

    @ManyToOne
    private AirlineEntity airline;
}
