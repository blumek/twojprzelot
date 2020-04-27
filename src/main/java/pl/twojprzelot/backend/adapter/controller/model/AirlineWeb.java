package pl.twojprzelot.backend.adapter.controller.model;

import lombok.*;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
public class AirlineWeb {
    private String id;
    private String name;
    private String iataCode;
    private String icaoCode;
}
