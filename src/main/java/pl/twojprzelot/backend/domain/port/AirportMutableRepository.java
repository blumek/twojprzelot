package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airport;

public interface AirportMutableRepository extends AirportImmutableRepository {
    Airport create(Airport airport);
}
