package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryImmutableRepository {
    List<Country> findAll();
    Optional<Country> findByIso2Code(String iso2Code);
}
