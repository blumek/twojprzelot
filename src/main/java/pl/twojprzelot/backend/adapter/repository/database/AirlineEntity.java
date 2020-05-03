package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Airline;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "airline")
class AirlineEntity extends BaseEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    private String name;

    @Column(unique = true)
    private String iataCode;

    @Column(unique = true)
    private String icaoCode;

    public Airline toAirline() {
        return mapper.mapToAirline(this);
    }
}
