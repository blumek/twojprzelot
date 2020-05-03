package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitySpringRepository extends JpaRepository<CityEntity, Integer> {
    Optional<CityEntity> findByIataCode(String iataCode);
}
