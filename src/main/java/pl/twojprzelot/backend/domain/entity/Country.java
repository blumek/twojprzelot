package pl.twojprzelot.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

import static lombok.AccessLevel.*;

@AllArgsConstructor(access = PRIVATE)
@Builder(toBuilder = true)
@Value
public class Country {
    String id;
    String name;
    String iso2Code;
    String iso3Code;
    int isoNumber;
    int population;
    Currency currency;
    Map<Language, String> nameTranslations;
}
