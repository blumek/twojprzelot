package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.port.CountryMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class CountryDatabaseRepository implements CountryMutableRepository {
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
}
