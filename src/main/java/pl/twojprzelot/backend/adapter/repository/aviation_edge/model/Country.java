package pl.twojprzelot.backend.adapter.repository.aviation_edge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
@ToString
class Country {
    @JsonProperty("countryId")
    private int id;

    @JsonProperty("nameCountry")
    private String name;

    @JsonProperty("codeIso2Country")
    private String iso2Code;

    @JsonProperty("codeIso3Country")
    private String iso3Code;

    @JsonProperty("codeFips")
    private String fipsCode;

    private String numericIso;
    private String population;

    @JsonProperty("capital")
    private String capitalName;

    private String continent;

    @JsonProperty("nameCurrency")
    private String currencyName;

    @JsonProperty("codeCurrency")
    private String currencyCode;

    private String phonePrefix;

    private Translations translations;

    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    static class Translations {
        private Map<String, String> country;
    }
}
