package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.Optional;

public interface AirlineRepository {
    Optional<Airline> findByIataCode(String iataCode);
    Optional<Airline> findByIcaoCode(String icaoCode);
}
