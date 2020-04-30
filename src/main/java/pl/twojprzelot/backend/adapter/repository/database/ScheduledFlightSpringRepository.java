package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.twojprzelot.backend.adapter.repository.database.model.ScheduledFlightEntity;

import java.util.List;

@Repository
public interface ScheduledFlightSpringRepository extends JpaRepository<ScheduledFlightEntity, String> {
    List<ScheduledFlightEntity> findAllByFlightIdentifier_IataNumber(String iataNumber);
    List<ScheduledFlightEntity> findAllByFlightIdentifier_IcaoNumber(String icaoNumber);
}
