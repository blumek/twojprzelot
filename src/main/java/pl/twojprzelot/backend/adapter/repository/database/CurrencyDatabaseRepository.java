package pl.twojprzelot.backend.adapter.repository.database;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.port.CurrencyRepository;

import java.util.Optional;

@RequiredArgsConstructor
class CurrencyDatabaseRepository implements CurrencyRepository {
    private final CurrencySpringRepository repository;

    @Override
    public Optional<Currency> findByCode(String code) {
        return repository.findByCode(code)
                .map(CurrencyEntity::toCurrency);
    }
}
