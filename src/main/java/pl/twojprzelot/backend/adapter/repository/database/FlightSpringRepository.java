package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightSpringRepository extends JpaRepository<FlightEntity, Integer> {
    List<FlightEntity> findAllByFlightIdentifierIataNumber(String iataNumber);
    List<FlightEntity> findAllByFlightIdentifierIcaoNumber(String icaoNumber);
}
