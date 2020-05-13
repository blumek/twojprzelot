package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "flight")
final class FlightEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
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
