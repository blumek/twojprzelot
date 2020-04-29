package pl.twojprzelot.backend.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.twojprzelot.backend.adapter.repository.model.ScheduledFlightEntity;

import java.util.Optional;

@Repository
public interface ScheduledFlightSpringRepository extends JpaRepository<ScheduledFlightEntity, String> {
    Optional<ScheduledFlightEntity> findDistinctTopByFlightIdentifier_IataNumber(String iataNumber);
    Optional<ScheduledFlightEntity> findDistinctTopByFlightIdentifier_IcaoNumber(String icaoNumber);
}
