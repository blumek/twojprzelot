package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.port.CountryMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Component
@RequiredArgsConstructor
final class CountryDatabaseRepository implements CountryMutableRepository {
    private final CountrySpringRepository repository;

    @Override
    public List<Country> findAll() {
        return repository.findAll().stream()
                .map(CountryEntity::toCountry)
                .collect(toList());
    }

    @Override
    public Optional<Country> findByIso2Code(@NonNull String iso2Code) {
        return repository.findByIso2Code(iso2Code)
                .map(CountryEntity::toCountry);
    }

    @Override
    public Country create(@NonNull Country country) {
        CountryEntity countryToCreate = CountryEntity.from(country);
        CountryEntity createdCountry = repository.save(countryToCreate);
        return createdCountry.toCountry();
    }

    @Override
    public List<Country> overrideAll(Iterable<Country> countries) {
        removeAllAndFlush();

        List<CountryEntity> countriesToCreate = stream(countries.spliterator(), false)
                .map(CountryEntity::from)
                .collect(toList());

        List<CountryEntity> createdCountries = repository.saveAll(countriesToCreate);

        return createdCountries.stream()
                .map(CountryEntity::toCountry)
                .collect(toList());
    }

    private void removeAllAndFlush() {
        repository.deleteAll();
        repository.flush();
    }

    @Override
    public Country update(@NonNull Country country) {
        if (!hasIdentifier(country))
            throw new IllegalArgumentException("Identifier not valid");

        CountryEntity countryToUpdate = CountryEntity.from(country);
        CountryEntity updatedCountry = repository.save(countryToUpdate);
        return updatedCountry.toCountry();
    }

    private boolean hasIdentifier(Country country) {
        return country.getId() > 0;
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
