package pl.twojprzelot.backend.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    private static final int ID = 2;
    private static final String NAME = "NAME";
    private static final String CODE = "CODE";
    private static final int ISO_NUMBER = 1;
    private static final String ANOTHER_CODE = "ANOTHER_CODE";
    private static final int ANOTHER_ISO_NUMBER = 2;

    private Currency firstCurrency;
    private Currency sameCurrencyAsFirstCurrency;
    private Currency anotherCurrency;

    @BeforeEach
    void setUp() {
        firstCurrency = Currency.builder()
                .id(ID)
                .name(NAME)
                .code(CODE)
                .isoNumber(ISO_NUMBER)
                .build();

        sameCurrencyAsFirstCurrency = Currency.builder()
                .id(ID)
                .name(NAME)
                .code(CODE)
                .isoNumber(ISO_NUMBER)
                .build();

        anotherCurrency = Currency.builder()
                .id(ID)
                .name(NAME)
                .code(ANOTHER_CODE)
                .isoNumber(ANOTHER_ISO_NUMBER)
                .build();
    }

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
    void equalsTest_equalObjects() {
        assertEquals(firstCurrency, sameCurrencyAsFirstCurrency);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstCurrency, anotherCurrency);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstCurrency.hashCode(), sameCurrencyAsFirstCurrency.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstCurrency.hashCode(), anotherCurrency.hashCode());
    }
}