package pl.twojprzelot.backend.adapter.repository.aviation_edge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
class AirplaneSpeedAE {
    private double horizontal;
    private double isGround;

    @JsonProperty("vspeed")
    private double vertical;
}
