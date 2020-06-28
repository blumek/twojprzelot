package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.City;

import java.util.List;

public interface CityMutableRepository extends CityImmutableRepository {
    City create(City city);
    List<City> overrideAll(Iterable<City> cities);
    City update(City city);
    void removeAll();
}
