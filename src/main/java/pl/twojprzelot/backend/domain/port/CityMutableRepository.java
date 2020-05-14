package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.City;

public interface CityMutableRepository extends CityImmutableRepository {
    City create(City city);
    City update(City city);
}
