package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.port.CountryRepository;

import java.util.Optional;

@RequiredArgsConstructor
class CountryDatabaseRepository implements CountryRepository {
    private final CountrySpringRepository repository;

    @Override
    public Optional<Country> findByIso2Code(String iso2Code) {
        return repository.findByIso2Code(iso2Code)
                .map(CountryEntity::toCountry);
    }
}
