package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Country;

import java.util.List;

public interface CountryMutableRepository extends CountryImmutableRepository {
    Country create(Country country);
    List<Country> overrideAll(Iterable<Country> countries);
    Country update(Country country);
    void removeAll();
}
