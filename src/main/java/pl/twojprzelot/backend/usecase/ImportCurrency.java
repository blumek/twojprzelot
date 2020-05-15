package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.port.CurrencyImmutableRepository;
import pl.twojprzelot.backend.domain.port.CurrencyMutableRepository;

import java.util.Optional;

@RequiredArgsConstructor
final class ImportCurrency {
    private final CurrencyImmutableRepository sourceRepository;
    private final CurrencyMutableRepository targetRepository;

    public void importAll() {
        sourceRepository.findAll().stream()
                .filter(this::hasCode)
                .forEach(currency -> {
                    Optional<Currency> foundCurrency = targetRepository.findByCode(currency.getCode());
                    if (foundCurrency.isPresent()) {
                        Currency currencyToUpdate = getCurrencyWithId(currency, foundCurrency.get().getId());
                        targetRepository.update(currencyToUpdate);
                    } else {
                        Currency currencyToSave = getCurrencyWithoutId(currency);
                        targetRepository.create(currencyToSave);
                    }
                });
    }

    private boolean hasCode(Currency currency) {
        return currency.getCode() != null;
    }

    private Currency getCurrencyWithId(Currency currency, int id) {
        return currency.toBuilder()
                .id(id)
                .build();
    }

    private Currency getCurrencyWithoutId(Currency currency) {
        return currency.toBuilder()
                .id(0)
                .build();
    }
}
