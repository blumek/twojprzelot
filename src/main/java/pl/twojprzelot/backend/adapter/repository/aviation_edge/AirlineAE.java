package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Airline;

@NoArgsConstructor
@Setter
@Getter
@ToString
class AirlineAE {
    private static final AirlineAEMapper mapper = Mappers.getMapper(AirlineAEMapper.class);

    @JsonProperty("airlineId")
    private int id;

    @JsonProperty("nameAirline")
    private String name;

    @JsonProperty("codeIataAirline")
    private String iataCode;

    @JsonProperty("codeIcaoAirline")
    private String icaoCode;

    @JsonProperty("codeIso2Country")
    private String countryIso2Code;

    @JsonProperty("iataPrefixAccounting")
    private String accountingIataPrefix;

    @JsonProperty("callsign")
    private String callSign;

    private String type;

    @JsonProperty("statusAirline")
    private String status;

    private int founding;

    @JsonProperty("codeHub")
    private String hubIataCode;

    @JsonProperty("sizeAirline")
    private int size;

    private double ageFleet;

    public Airline toAirline() {
        return mapper.mapToAirline(this);
    }
}
