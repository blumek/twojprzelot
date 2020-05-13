package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
final class AirplaneSpeedAE {
    private double horizontal;
    private double isGround;

    @JsonProperty("vspeed")
    private double vertical;
}
