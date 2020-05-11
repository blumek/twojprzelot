package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Country;

public interface CountryMutableRepository extends CountryImmutableRepository {
    Country create(Country country);
}
