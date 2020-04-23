package pl.twojprzelot.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@Builder(toBuilder = true)
@Value
public class City {
    String name;
    String iataCode;
    GeographicLocation geographicLocation;
    Country country;
    Map<Language, String> nameTranslations;
}
