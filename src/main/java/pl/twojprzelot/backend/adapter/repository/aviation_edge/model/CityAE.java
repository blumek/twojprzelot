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
class CityAE {
    @JsonProperty("cityId")
    private int id;

    @JsonProperty("nameCity")
    private String name;

    @JsonProperty("codeIataCity")
    private String iataCode;

    @JsonProperty("codeIso2Country")
    private String countryIso2Code;

    @JsonProperty("latitudeCity")
    private double latitude;

    @JsonProperty("longitudeCity")
    private double longitude;

    private String timezone;

    @JsonProperty("GMT")
    private String gmt;

    private int geonameId;
    private Translations translations;

    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    static class Translations {
        private Map<String, String> country;
        private Map<String, String> city;
    }
}
