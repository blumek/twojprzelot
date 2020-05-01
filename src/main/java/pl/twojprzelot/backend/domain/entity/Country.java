package pl.twojprzelot.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Builder(toBuilder = true)
@Value
public class Country {
    int id;
    String name;
    String iso2Code;
    String iso3Code;
    int isoNumber;
    int population;
    Currency currency;
}
