package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Currency;

import java.util.Optional;

public interface CurrencyRepository {
    Optional<Currency> findByCode(String code);
}
