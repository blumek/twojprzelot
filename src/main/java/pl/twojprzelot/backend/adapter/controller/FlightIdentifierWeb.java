package pl.twojprzelot.backend.adapter.controller;

import lombok.*;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
final class FlightIdentifierWeb {
    private int number;
    private String iataNumber;
    private String icaoNumber;
}
