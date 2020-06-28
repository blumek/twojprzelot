package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.port.CityMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
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

    @Transactional
    @Override
    public List<City> overrideAll(List<City> cities) {
        removeAllAndFlush();

        List<CityEntity> citiesToCreate = cities.stream()
                .map(CityEntity::from)
                .collect(toList());

        List<CityEntity> createdCities = repository.saveAll(citiesToCreate);

        return createdCities.stream()
                .map(CityEntity::toCity)
                .collect(toList());
    }

    private void removeAllAndFlush() {
        repository.deleteAll();
        repository.flush();
    }

    @Override
    public City update(@NonNull City city) {
        if (!hasIdentifier(city))
            throw new IllegalArgumentException("Identifier not valid");

        CityEntity cityToUpdate = CityEntity.from(city);
        CityEntity updatedCity = repository.save(cityToUpdate);
        return updatedCity.toCity();
    }

    private boolean hasIdentifier(City city) {
        return city.getId() > 0;
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
