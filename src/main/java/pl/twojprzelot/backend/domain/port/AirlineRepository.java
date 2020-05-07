package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository {
    List<Airline> findAll();
    Optional<Airline> findByIataCode(String iataCode);
    Optional<Airline> findByIcaoCode(String icaoCode);
}
