package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledFlightSpringRepository extends JpaRepository<ScheduledFlightEntity, Integer> {
    List<ScheduledFlightEntity> findAllByFlightIdentifierIataNumber(String iataNumber);
    List<ScheduledFlightEntity> findAllByFlightIdentifierIcaoNumber(String icaoNumber);
}
