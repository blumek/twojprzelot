package pl.twojprzelot.backend.adapter.repository.model;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.twojprzelot.backend.domain.entity.Language;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.EnumType.STRING;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "country")
public class CountryEntity extends BaseEntity {
    private String name;
    private String iso2Code;
    private String iso3Code;
    private int isoNumber;
    private int population;

    @ManyToOne
    private CurrencyEntity currency;

    @ElementCollection
    @MapKeyEnumerated(STRING)
    @MapKeyColumn(name = "language")
    @Column(name = "translation")
    private Map<Language, String> nameTranslations = Maps.newEnumMap(Language.class);
}
