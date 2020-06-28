package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.List;

public interface AirlineMutableRepository extends AirlineImmutableRepository {
    Airline create(Airline airline);
    List<Airline> overrideAll(Iterable<Airline> airlines);
    Airline update(Airline airline);
    void removeAll();
}
