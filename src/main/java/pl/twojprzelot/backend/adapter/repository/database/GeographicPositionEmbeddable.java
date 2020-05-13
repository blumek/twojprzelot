package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
final class GeographicPositionEmbeddable {
    private double latitude;
    private double longitude;
    private double altitude;
    private double direction;
}
