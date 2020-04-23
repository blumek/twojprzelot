package pl.twojprzelot.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@Builder(toBuilder = true)
@Value
public class Airport {
    String name;
    String iataCode;
    String icaoCode;
    GeographicLocation geographicLocation;
    City city;
    Map<Language, String> nameTranslations;
}
