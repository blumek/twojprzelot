package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.City;

import java.util.Optional;

public interface CityRepository {
    Optional<City> findByIataCode(String iataCode);
}
