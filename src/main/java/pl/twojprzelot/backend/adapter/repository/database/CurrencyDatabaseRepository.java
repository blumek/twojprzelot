package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.port.CurrencyMutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
final class CurrencyDatabaseRepository implements CurrencyMutableRepository {
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
}
