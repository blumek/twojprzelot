package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Currency;

import java.util.List;

public interface CurrencyMutableRepository extends CurrencyImmutableRepository {
    Currency create(Currency currency);
    List<Currency> overrideAll(Iterable<Currency> currencies);
    Currency update(Currency currency);
    void removeAll();
}
