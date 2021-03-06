package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
final class FlightAirportDetailsEmbeddable {
    private String gate;
    private String terminal;
}
