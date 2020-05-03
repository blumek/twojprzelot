package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportSpringRepository extends JpaRepository<AirportEntity, Integer> {
    Optional<AirportEntity> findByIataCode(String iataCode);
    Optional<AirportEntity> findByIcaoCode(String icaoCode);
}
