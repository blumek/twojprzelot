package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyEntityTest {
    private static final String ID = "ID";
    private static final String ANOTHER_ID = "ANOTHER_ID";
    private static final String NAME = "NAME";
    private static final int ISO_NUMBER = 10;
    private static final String CODE = "CODE";

    private CurrencyEntity firstCurrencyEntity;
    private CurrencyEntity sameCurrencyEntityAsFirstCurrencyEntity;
    private CurrencyEntity anotherCurrencyEntity;

    @BeforeEach
    void setUp() {
        firstCurrencyEntity = new CurrencyEntity();
        firstCurrencyEntity.setId(ID);
        firstCurrencyEntity.setName(NAME);
        firstCurrencyEntity.setIsoNumber(ISO_NUMBER);
        firstCurrencyEntity.setCode(CODE);

        sameCurrencyEntityAsFirstCurrencyEntity = new CurrencyEntity();
        sameCurrencyEntityAsFirstCurrencyEntity.setId(ID);
        sameCurrencyEntityAsFirstCurrencyEntity.setName(NAME);
        sameCurrencyEntityAsFirstCurrencyEntity.setIsoNumber(ISO_NUMBER);
        sameCurrencyEntityAsFirstCurrencyEntity.setCode(CODE);

        anotherCurrencyEntity = new CurrencyEntity();
        anotherCurrencyEntity.setId(ANOTHER_ID);
        anotherCurrencyEntity.setName(NAME);
        anotherCurrencyEntity.setIsoNumber(ISO_NUMBER);
        anotherCurrencyEntity.setCode(CODE);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstCurrencyEntity, sameCurrencyEntityAsFirstCurrencyEntity);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstCurrencyEntity, anotherCurrencyEntity);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstCurrencyEntity.hashCode(), sameCurrencyEntityAsFirstCurrencyEntity.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstCurrencyEntity.hashCode(), anotherCurrencyEntity.hashCode());
    }
}