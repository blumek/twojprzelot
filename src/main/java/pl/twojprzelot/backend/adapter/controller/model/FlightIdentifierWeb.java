package pl.twojprzelot.backend.adapter.controller.model;

import lombok.*;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public class FlightIdentifierWeb {
    private int number;
    private String iataNumber;
    private String icaoNumber;
}
