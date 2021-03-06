package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "airport")
final class AirportEntity{
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String iataCode;

    private String icaoCode;

    @Embedded
    private GeographicLocationEmbeddable geographicLocation;

    @ManyToOne
    private CityEntity city;

    @ElementCollection
    @MapKeyEnumerated(STRING)
    @MapKeyColumn(name = "language")
    @Column(name = "translation")
    private Map<Language, String> nameTranslations = Maps.newEnumMap(Language.class);

    @OneToMany(mappedBy = "arrival.airport", cascade = CascadeType.REMOVE)
    private List<ScheduledFlightEntity> arrivalScheduledFlights;

    @OneToMany(mappedBy = "departure.airport", cascade = CascadeType.REMOVE)
    private List<ScheduledFlightEntity> departureScheduledFlights;

    public Airport toAirport() {
        return mapper.mapToAirport(this);
    }

    public static AirportEntity from(Airport airport) {
        return mapper.mapToAirportEntity(airport);
    }
}
