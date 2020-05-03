package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airport;

import java.util.Optional;

public interface AirportRepository {
    Optional<Airport> findByIataCode(String iataCode);
    Optional<Airport> findByIcaoCode(String icaoCode);
}
