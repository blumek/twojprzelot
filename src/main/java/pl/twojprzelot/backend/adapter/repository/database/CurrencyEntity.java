package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "currency")
class CurrencyEntity extends BaseEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

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
