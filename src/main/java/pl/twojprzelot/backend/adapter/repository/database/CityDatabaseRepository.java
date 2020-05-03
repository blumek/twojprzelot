package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.port.CityRepository;

import java.util.Optional;

@RequiredArgsConstructor
class CityDatabaseRepository implements CityRepository {
    private final CitySpringRepository repository;

    @Override
    public Optional<City> findByIataCode(String iataCode) {
        return repository.findByIataCode(iataCode)
                .map(CityEntity::toCity);
    }
}
