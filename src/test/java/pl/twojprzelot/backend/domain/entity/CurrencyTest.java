package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyTest {
    private static final int ID = 2;
    private static final String NAME = "NAME";
    private static final String CODE = "CODE";
    private static final int ISO_NUMBER = 1;

    @Test
    void builderTest_id() {
        Currency currency = Currency.builder()
                .id(ID)
                .build();

            assertEquals(ID, currency.getId());
    }

    @Test
    void builderTest_name() {
        Currency currency = Currency.builder()
                .name(NAME)
                .build();

        assertEquals(NAME, currency.getName());
    }

    @Test
    void builderTest_code() {
        Currency currency = Currency.builder()
                .code(CODE)
                .build();

        assertEquals(CODE, currency.getCode());
    }

    @Test
    void builderTest_number() {
        Currency currency = Currency.builder()
                .isoNumber(ISO_NUMBER)
                .build();

        assertEquals(ISO_NUMBER, currency.getIsoNumber());
    }

    @Test
    void builderTest_toBuilder() {
        Currency currency = Currency.builder()
                .name(NAME)
                .isoNumber(ISO_NUMBER)
                .build();

        Currency anotherCurrency = currency.toBuilder()
                .code(CODE)
                .build();

        Currency expectedCurrency = Currency.builder()
                .name(NAME)
                .code(CODE)
                .isoNumber(ISO_NUMBER)
                .build();

        assertEquals(expectedCurrency, anotherCurrency);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(Currency.class).verify();
    }
}