package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airline;

public interface AirlineMutableRepository extends AirlineImmutableRepository {
    Airline create(Airline airline);
    Airline update(Airline airline);
}
