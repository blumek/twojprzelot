package pl.twojprzelot.backend.adapter.imports;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.CurrencyImmutableRepository;
import pl.twojprzelot.backend.domain.port.CurrencyMutableRepository;
import pl.twojprzelot.backend.domain.port.ImportCurrency;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
final class SimpleImportCurrency implements ImportCurrency {
    private final CurrencyImmutableRepository sourceRepository;
    private final CurrencyMutableRepository targetRepository;

    @Override
    public void importAll() {
        log.info("Importing all countries");

        sourceRepository.findAll().stream()
                .filter(this::hasCode)
                .forEach(importedCurrency -> {
                    Optional<Currency> alreadyCreatedCurrency = targetRepository.findByCode(importedCurrency.getCode());

                    if (alreadyCreatedCurrency.isPresent())
                        updateCurrency(importedCurrency, alreadyCreatedCurrency.get());
                    else
                        createCurrency(importedCurrency);
                });

        log.info("Imported all countries");
    }

    private boolean hasCode(Currency currency) {
        return currency.getCode() != null;
    }

    private void updateCurrency(Currency currency, Currency alreadyCreatedCurrency) {
        Currency currencyToUpdate = getCurrencyWithId(currency, alreadyCreatedCurrency.getId());
        targetRepository.update(currencyToUpdate);
    }

    private Currency getCurrencyWithId(Currency currency, int id) {
        return currency.toBuilder()
                .id(id)
                .build();
    }

    private void createCurrency(Currency currency) {
        Currency currencyToSave = getCurrencyWithoutId(currency);
        targetRepository.create(currencyToSave);
    }

    private Currency getCurrencyWithoutId(Currency currency) {
        return currency.toBuilder()
                .id(0)
                .build();
    }

    @Override
    public void overrideAll() {
        log.info("Overriding all currencies");

        List<Currency> importedCurrencies = sourceRepository.findAll();
        if (importedCurrencies.isEmpty())
            throw new ImportException("No currencies to import");

        List<Currency> currenciesToCreate = importedCurrencies.stream()
                .map(this::getCurrencyWithoutId)
                .collect(toList());

        targetRepository.overrideAll(currenciesToCreate);

        log.info("Overridden all currencies");
    }
}
