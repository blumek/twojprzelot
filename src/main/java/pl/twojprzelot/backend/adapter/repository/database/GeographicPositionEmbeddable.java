package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
class GeographicPositionEmbeddable {
    private double latitude;
    private double longitude;
    private double altitude;
    private double direction;
}
