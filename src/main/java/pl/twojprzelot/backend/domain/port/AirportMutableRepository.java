package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airport;

import java.util.List;

public interface AirportMutableRepository extends AirportImmutableRepository {
    Airport create(Airport airport);
    List<Airport> overrideAll(Iterable<Airport> airports);
    Airport update(Airport airport);
    void removeAll();
}
