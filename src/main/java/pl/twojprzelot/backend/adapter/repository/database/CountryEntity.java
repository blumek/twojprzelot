package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Maps;
import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.EnumType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "country")
class CountryEntity extends BaseEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

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

    public Country toCountry() {
        return mapper.mapToCountry(this);
    }

    public static CountryEntity from(Country country) {
        return mapper.mapToCountryEntity(country);
    }
}
