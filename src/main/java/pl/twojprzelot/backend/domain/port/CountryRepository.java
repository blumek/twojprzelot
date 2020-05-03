package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Country;

import java.util.Optional;

public interface CountryRepository {
    Optional<Country> findByIso2Code(String iso2Code);
}
