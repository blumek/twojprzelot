package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Flight;

import java.util.List;

public interface FlightMutableRepository extends FlightImmutableRepository {
    Flight create(Flight flight);
    List<Flight> overrideAll(Iterable<Flight> flights);
    Flight update(Flight flight);
    void removeAll();
}
