package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.*;

@Data
@NoArgsConstructor
final class FlightIdentifierAE {
    private String number;
    private String iataNumber;
    private String icaoNumber;
}
