package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Maps;
import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.EnumType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "airport")
class AirportEntity extends BaseEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    private String name;

    @Column(unique = true)
    private String iataCode;

    @Column(unique = true)
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

    public Airport toAirport() {
        return mapper.mapToAirport(this);
    }
}
