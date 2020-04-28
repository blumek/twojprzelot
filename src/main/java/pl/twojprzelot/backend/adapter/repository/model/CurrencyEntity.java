package pl.twojprzelot.backend.adapter.repository.model;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity(name = "currency")
public class CurrencyEntity extends BaseEntity {
    String name;
    String code;
    int isoNumber;
}
