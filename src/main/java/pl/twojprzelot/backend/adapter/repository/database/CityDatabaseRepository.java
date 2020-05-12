package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.port.CityMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class CityDatabaseRepository implements CityMutableRepository {
    private final CitySpringRepository repository;

    @Override
    public List<City> findAll() {
        return repository.findAll().stream()
                .map(CityEntity::toCity)
                .collect(toList());
    }

    @Override
    public Optional<City> findByIataCode(@NonNull String iataCode) {
        return repository.findByIataCode(iataCode)
                .map(CityEntity::toCity);
    }

    @Override
    public City create(@NonNull City city) {
        CityEntity cityToCreate = CityEntity.from(city);
        CityEntity createdCity = repository.save(cityToCreate);
        return createdCity.toCity();
    }
}
