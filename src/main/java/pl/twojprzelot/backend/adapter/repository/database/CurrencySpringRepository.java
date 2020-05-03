package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencySpringRepository extends JpaRepository<CurrencyEntity, Integer> {
    Optional<CurrencyEntity> findByCode(String code);
}
