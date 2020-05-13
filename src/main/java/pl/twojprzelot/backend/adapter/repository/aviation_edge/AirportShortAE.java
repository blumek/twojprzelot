package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.*;

@Data
@NoArgsConstructor
final class AirportShortAE {
    private String iataCode;
    private String icaoCode;
}
