package pl.twojprzelot.backend.adapter.controller;

import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
final class GeographicPositionWeb {
    private double latitude;
    private double longitude;
    private double altitude;
    private double direction;
}
