package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
class AircraftShortAE {
    private String iataCode;
    private String icaoCode;
    private String icao24;
    private String regNumber;
}