package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Maps;
import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.EnumType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "city")
class CityEntity extends BaseEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    private String name;

    @Column(unique = true)
    private String iataCode;

    @Embedded
    private GeographicLocationEmbeddable geographicLocation;

    @ManyToOne
    private CountryEntity country;

    @ElementCollection
    @MapKeyEnumerated(STRING)
    @MapKeyColumn(name = "language")
    @Column(name = "translation")
    private Map<Language, String> nameTranslations = Maps.newEnumMap(Language.class);

    public City toCity() {
        return mapper.mapToCity(this);
    }
}
