package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Maps;
import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "country")
final class CountryEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String iso2Code;

    @Column(unique = true)
    private String iso3Code;

    @Column(unique = true)
    private int isoNumber;

    private int population;

    @ManyToOne
    private CurrencyEntity currency;

    @ElementCollection
    @MapKeyEnumerated(STRING)
    @MapKeyColumn(name = "language")
    @Column(name = "translation")
    private Map<Language, String> nameTranslations = Maps.newEnumMap(Language.class);

    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
    private List<CityEntity> cities;

    public Country toCountry() {
        return mapper.mapToCountry(this);
    }

    public static CountryEntity from(Country country) {
        return mapper.mapToCountryEntity(country);
    }
}
