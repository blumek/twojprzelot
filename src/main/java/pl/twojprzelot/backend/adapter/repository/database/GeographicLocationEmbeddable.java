package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
final class GeographicLocationEmbeddable {
    private double latitude;
    private double longitude;
}
