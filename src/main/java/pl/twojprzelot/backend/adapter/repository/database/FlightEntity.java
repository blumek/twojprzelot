package pl.twojprzelot.backend.adapter.repository.database;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import pl.twojprzelot.backend.domain.entity.Flight;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity(name = "flight")
final class FlightEntity {
    private static final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Embedded
    private FlightIdentifierEmbeddable flightIdentifier;

    @Embedded
    private GeographicPositionEmbeddable geographicPosition;

    @Embedded
    private AirplaneSpeedEmbeddable airplaneSpeed;

    public static FlightEntity from(Flight flight) {
        return mapper.mapToFlightEntity(flight);
    }

    public Flight toFlight() {
        return mapper.mapFromFlightEntity(this);
    }
}
