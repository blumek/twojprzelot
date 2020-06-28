package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.port.CurrencyMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Component
@RequiredArgsConstructor
class CurrencyDatabaseRepository implements CurrencyMutableRepository {
    private final CurrencySpringRepository repository;

    @Override
    public List<Currency> findAll() {
        return repository.findAll().stream()
                .map(CurrencyEntity::toCurrency)
                .collect(toList());
    }

    @Override
    public Optional<Currency> findByCode(@NonNull String code) {
        return repository.findByCode(code)
                .map(CurrencyEntity::toCurrency);
    }

    @Override
    public Optional<Currency> findByIsoNumber(int isoNumber) {
        if (!isValidIdentifier(isoNumber))
            throw new IllegalArgumentException("Invalid identifier");

        return repository.findByIsoNumber(isoNumber)
                .map(CurrencyEntity::toCurrency);
    }

    private boolean isValidIdentifier(int isoNumber) {
        return isoNumber > 0;
    }

    @Override
    public Currency create(@NonNull Currency currency) {
        CurrencyEntity currencyToCreate = CurrencyEntity.from(currency);
        CurrencyEntity createdCurrency = repository.save(currencyToCreate);
        return createdCurrency.toCurrency();
    }

    @Transactional
    @Override
    public List<Currency> overrideAll(@NonNull Iterable<Currency> currencies) {
        removeAllAndFlush();

        List<CurrencyEntity> currenciesToCreate = stream(currencies.spliterator(), false)
                .map(CurrencyEntity::from)
                .collect(toList());

        List<CurrencyEntity> createdCurrencies = repository.saveAll(currenciesToCreate);

        return createdCurrencies.stream()
                .map(CurrencyEntity::toCurrency)
                .collect(toList());
    }

    private void removeAllAndFlush() {
        repository.deleteAll();
        repository.flush();
    }

    @Override
    public Currency update(@NonNull Currency currency) {
        if (!hasIdentifier(currency))
            throw new IllegalArgumentException("Identifier not valid");

        CurrencyEntity currencyToUpdate = CurrencyEntity.from(currency);
        CurrencyEntity updatedCurrency = repository.save(currencyToUpdate);
        return updatedCurrency.toCurrency();
    }

    private boolean hasIdentifier(Currency currency) {
        return currency.getId() > 0;
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
