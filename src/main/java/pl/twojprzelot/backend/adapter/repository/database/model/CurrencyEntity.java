package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "currency")
public class CurrencyEntity {
    @Id
    private int id;
    private String name;
    private String code;
    private int isoNumber;
}
