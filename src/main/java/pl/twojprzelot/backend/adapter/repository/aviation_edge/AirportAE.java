package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Airport;

import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
@ToString
class AirportAE {
    private static final AviationEdgeMapper mapper = Mappers.getMapper(AviationEdgeMapper.class);

    @JsonProperty("airportId")
    private int id;

    @JsonProperty("nameAirport")
    private String name;

    @JsonProperty("codeIataAirport")
    private String iataCode;

    @JsonProperty("codeIcaoAirport")
    private String icaoCode;

    @JsonProperty("codeIso2Country")
    private String countryIso2Code;

    @JsonProperty("codeIataCity")
    private String cityIataCode;

    @JsonProperty("latitudeAirport")
    private double latitude;

    @JsonProperty("longitudeAirport")
    private double longitude;

    private String timezone;

    @JsonProperty("GMT")
    private String gmt;

    private int geonameId;
    private String phone;

    @JsonProperty("nameCountry")
    private String countryName;

    private Translations translations;

    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    static class Translations {
        private Map<String, String> country;
        private Map<String, String> city;
        private Map<String, String> airport;
    }

    public Airport toAirport() {
        return mapper.mapToAirport(this);
    }
}
