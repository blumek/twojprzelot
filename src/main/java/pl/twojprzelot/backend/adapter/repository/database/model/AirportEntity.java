package pl.twojprzelot.backend.adapter.repository.database.model;

import com.google.common.collect.Maps;
import lombok.*;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.EnumType.STRING;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "airport")
public class AirportEntity {
    @Id
    private int id;
    private String name;
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
}
