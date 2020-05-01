package pl.twojprzelot.backend.adapter.repository.aviation_edge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
class FlightIdentifier {
    private String number;
    private String iataNumber;
    private String icaoNumber;
}