package pl.twojprzelot.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Builder(toBuilder = true)
@Value
public class Airport {
    String id;
    String name;
    String iataCode;
    String icaoCode;
    GeographicLocation geographicLocation;
    City city;
}
