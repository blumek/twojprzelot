package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
class FlightIdentifierEmbeddable {
    private int number;
    private String iataNumber;
    private String icaoNumber;
}
