package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
final class AirplaneSpeedEmbeddable {
    private double horizontalSpeed;
    private double verticalSpeed;
}
