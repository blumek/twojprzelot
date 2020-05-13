package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Maps;
import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "city")
final class CityEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;

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

    public static CityEntity from(City city) {
        return mapper.mapToCityEntity(city);
    }
}
