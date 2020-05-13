package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.*;

@Data
@NoArgsConstructor
final class AircraftShortAE {
    private String iataCode;
    private String icaoCode;
    private String icao24;
    private String regNumber;
}
