package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
final class GeographicPositionEmbeddable {
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double direction;
}
