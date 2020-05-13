package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Currency;

public interface CurrencyMutableRepository extends CurrencyImmutableRepository {
    Currency create(Currency currency);
    Currency update(Currency currency);
}
