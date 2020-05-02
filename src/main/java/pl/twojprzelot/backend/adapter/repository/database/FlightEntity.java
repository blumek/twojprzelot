package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity(name = "flight")
class FlightEntity extends BaseEntity {
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
