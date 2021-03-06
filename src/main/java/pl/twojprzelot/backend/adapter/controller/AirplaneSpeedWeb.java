package pl.twojprzelot.backend.adapter.controller;

import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
final class AirplaneSpeedWeb {
    private double horizontalSpeed;
    private double verticalSpeed;
}
