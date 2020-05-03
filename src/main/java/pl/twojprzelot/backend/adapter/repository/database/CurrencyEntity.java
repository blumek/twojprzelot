package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "currency")
class CurrencyEntity extends BaseEntity {
    private String name;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private int isoNumber;
}
