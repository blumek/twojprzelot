package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.port.CurrencyRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class CurrencyDatabaseRepository implements CurrencyRepository {
    private final CurrencySpringRepository repository;

    @Override
    public Optional<Currency> findByCode(String code) {
        return repository.findByCode(code)
                .map(CurrencyEntity::toCurrency);
    }

    @Override
    public List<Currency> findAll() {
        return repository.findAll().stream()
                .map(CurrencyEntity::toCurrency)
                .collect(toList());
    }
}
