package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.Test;
import pl.twojprzelot.backend.domain.entity.Currency;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyEntityTest {
    private static final int ID = 1;
    private static final int ISO_NUMBER = 10;
    private static final String CODE = "CODE";
    private static final String NAME = "NAME";

    @Test
    void toCurrencyTest() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(ID);
        currencyEntity.setIsoNumber(ISO_NUMBER);
        currencyEntity.setCode(CODE);
        currencyEntity.setName(NAME);

        Currency currency = Currency.builder()
                .id(ID)
                .isoNumber(ISO_NUMBER)
                .code(CODE)
                .name(NAME)
                .build();

        assertEquals(currency, currencyEntity.toCurrency());
    }
}