package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.*;

@Data
@NoArgsConstructor
final class GeographicPositionAE {
    private double latitude;
    private double longitude;
    private double altitude;
    private double direction;
}
