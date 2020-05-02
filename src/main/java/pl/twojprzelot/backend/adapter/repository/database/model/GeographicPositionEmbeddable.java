package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class GeographicPositionEmbeddable {
    private double latitude;
    private double longitude;
    private double altitude;
    private double direction;
}
