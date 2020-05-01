package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
class GeographicPositionAE {
    private double latitude;
    private double longitude;
    private double altitude;
    private double direction;
}