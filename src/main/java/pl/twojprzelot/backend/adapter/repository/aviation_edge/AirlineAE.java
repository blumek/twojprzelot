package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
class AirlineAE {
    private int airlineId;
    @JsonProperty("nameAirline")
    private String airlineName;

    @JsonProperty("codeIataAirline")
    private String airlineIataCode;

    @JsonProperty("codeIcaoAirline")
    private String airlineIcaoCode;

    @JsonProperty("codeIso2Country")
    private String countryIso2Code;

    @JsonProperty("iataPrefixAccounting")
    private String accountingIataPrefix;

    private String callSign;
    private String type;

    @JsonProperty("statusAirline")
    private String airlineStatus;

    private int founding;

    @JsonProperty("codeIataHub")
    private String hubIataCode;

    @JsonProperty("sizeAirline")
    private int airlineSize;

    private double ageFleet;
}
