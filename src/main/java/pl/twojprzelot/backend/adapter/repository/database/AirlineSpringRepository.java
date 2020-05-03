package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineSpringRepository extends JpaRepository<AirlineEntity, Integer> {
    Optional<AirlineEntity> findByIataCode(String iataCode);
    Optional<AirlineEntity> findByIcaoCode(String icaoCode);
}
