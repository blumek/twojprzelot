package pl.twojprzelot.backend.adapter.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "currency")
public class CurrencyEntity extends BaseEntity {
    String name;
    String code;
    int isoNumber;
}
