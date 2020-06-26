package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Flight;

public interface FlightMutableRepository extends FlightImmutableRepository {
    Flight create(Flight flight);
    Flight update(Flight flight);
    void removeAll();
}
