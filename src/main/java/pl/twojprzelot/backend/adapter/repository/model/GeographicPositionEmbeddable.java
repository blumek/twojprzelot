package pl.twojprzelot.backend.adapter.repository.model;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class GeographicPositionEmbeddable {
    double latitude;
    double longitude;
    double altitude;
    double direction;
}
