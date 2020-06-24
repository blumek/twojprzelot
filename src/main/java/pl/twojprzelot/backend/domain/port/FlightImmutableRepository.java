package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Flight;

import java.util.List;

public interface FlightImmutableRepository {
    List<Flight> findAll();
}
