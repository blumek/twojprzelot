package pl.twojprzelot.backend.adapter.repository.database.model;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity(name = "airline")
public class AirlineEntity extends BaseEntity {
    private String name;
    private String iataCode;
    private String icaoCode;
}
