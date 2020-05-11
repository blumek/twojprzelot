package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Country;

public interface MutableCountryRepository extends ImmutableCountryRepository {
    Country create(Country country);
}
