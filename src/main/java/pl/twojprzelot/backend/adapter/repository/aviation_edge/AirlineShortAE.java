package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.*;

@Data
@NoArgsConstructor
final class AirlineShortAE {
    private String iataCode;
    private String icaoCode;
    private String name;
}
