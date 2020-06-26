package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSpringRepository extends JpaRepository<FlightEntity, Integer> {
}
