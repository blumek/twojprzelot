package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "currency")
final class CurrencyEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private int isoNumber;

    public Currency toCurrency() {
        return mapper.mapToCurrency(this);
    }

    public static CurrencyEntity from(Currency currency) {
        return mapper.mapToCurrencyEntity(currency);
    }
}
