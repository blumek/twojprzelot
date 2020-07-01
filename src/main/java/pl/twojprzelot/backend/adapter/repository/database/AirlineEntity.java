package pl.twojprzelot.backend.adapter.repository.database;

import lombok.*;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Airline;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "airline")
final class AirlineEntity{
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String name;

    private String iataCode;
    private String icaoCode;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ScheduledFlightEntity> scheduledFlights;

    public Airline toAirline() {
        return mapper.mapToAirline(this);
    }

    public static AirlineEntity from(Airline airline) {
        return mapper.mapToAirlineEntity(airline);
    }
}
