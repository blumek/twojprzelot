package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity(name = "currency")
public class CurrencyEntity extends BaseEntity {
    private String name;
    private String code;
    private int isoNumber;
}
