package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity(name = "airline")
class AirlineEntity extends BaseEntity {
    private String name;
    private String iataCode;
    private String icaoCode;
}
