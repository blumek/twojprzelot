package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    List<City> findAll();
    Optional<City> findByIataCode(String iataCode);
}
