package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class FlightIdentifierEmbeddable {
    int number;
    String iataNumber;
    String icaoNumber;
}