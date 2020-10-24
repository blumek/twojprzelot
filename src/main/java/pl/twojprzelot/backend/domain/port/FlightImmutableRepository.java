package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Flight;

import java.util.List;

public interface FlightImmutableRepository {
    List<Flight> findAll();

    List<Flight> findAllByIataNumber(String iataNumber);

    List<Flight> findAllByIcaoNumber(String icaoNumber);
}
