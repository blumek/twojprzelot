package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountrySpringRepository extends JpaRepository<CountryEntity, Integer> {
    Optional<CountryEntity> findByIso2Code(String iso2Code);
}
