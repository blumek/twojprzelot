package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "flight")
public class FlightEntity {
    @Id
    private int id;

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
