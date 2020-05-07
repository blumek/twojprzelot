package pl.twojprzelot.backend.domain.port;

import pl.twojprzelot.backend.domain.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    List<Currency> findAll();
    Optional<Currency> findByCode(String code);
}
