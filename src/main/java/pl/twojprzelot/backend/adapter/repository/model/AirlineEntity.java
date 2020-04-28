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
@Entity(name = "airline")
public class AirlineEntity extends BaseEntity {
    private String name;
    private String iataCode;
    private String icaoCode;
}
