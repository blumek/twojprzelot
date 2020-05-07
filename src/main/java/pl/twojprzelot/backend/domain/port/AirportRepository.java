package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {
    List<Airport> findAll();
    Optional<Airport> findByIataCode(String iataCode);
    Optional<Airport> findByIcaoCode(String icaoCode);
}
