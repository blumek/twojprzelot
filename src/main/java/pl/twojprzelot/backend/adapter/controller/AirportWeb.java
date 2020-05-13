package pl.twojprzelot.backend.adapter.controller;

import lombok.*;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
final class AirportWeb {
    private int id;
    private String name;
    private String iataCode;
    private String icaoCode;
    private String cityName;
    private String countryName;
}
