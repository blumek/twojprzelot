package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineImmutableRepository {
    List<Airline> findAll();
    List<Airline> findAllByIataCode(String iataCode);
    Optional<Airline> findByIcaoCode(String icaoCode);
}
