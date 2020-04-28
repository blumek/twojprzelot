package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pl.twojprzelot.backend.domain.entity.Language.POLISH;

class CountryEntityTest {
    private static final String CURRENCY_ID = "CURRENCY_ID";
    private static final String ID = "ID";
    private static final String ANOTHER_ID = "ANOTHER_ID";
    private static final int ISO_NUMBER = 1;
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final String ISO_3_CODE = "ISO_3_CODE";
    private static final int POPULATION = 100;
    private static final String NAME_TRANSLATION = "NAME_TRANSLATION";

    private CountryEntity firstCountryEntity;
    private CountryEntity sameCountryEntityAsFirstCountryEntity;
    private CountryEntity anotherCountryEntity;

    @BeforeEach
    void setUp() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(CURRENCY_ID);

        firstCountryEntity = new CountryEntity();
        firstCountryEntity.setId(ID);
        firstCountryEntity.setIsoNumber(ISO_NUMBER);
        firstCountryEntity.setIso2Code(ISO_2_CODE);
        firstCountryEntity.setIso3Code(ISO_3_CODE);
        firstCountryEntity.setPopulation(POPULATION);
        firstCountryEntity.setCurrency(currencyEntity);
        firstCountryEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);

        sameCountryEntityAsFirstCountryEntity = new CountryEntity();
        sameCountryEntityAsFirstCountryEntity.setId(ID);
        sameCountryEntityAsFirstCountryEntity.setIsoNumber(ISO_NUMBER);
        sameCountryEntityAsFirstCountryEntity.setIso2Code(ISO_2_CODE);
        sameCountryEntityAsFirstCountryEntity.setIso3Code(ISO_3_CODE);
        sameCountryEntityAsFirstCountryEntity.setPopulation(POPULATION);
        sameCountryEntityAsFirstCountryEntity.setCurrency(currencyEntity);
        sameCountryEntityAsFirstCountryEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);

        anotherCountryEntity = new CountryEntity();
        anotherCountryEntity.setId(ANOTHER_ID);
        anotherCountryEntity.setIsoNumber(ISO_NUMBER);
        anotherCountryEntity.setIso2Code(ISO_2_CODE);
        anotherCountryEntity.setIso3Code(ISO_3_CODE);
        anotherCountryEntity.setPopulation(POPULATION);
        anotherCountryEntity.setCurrency(currencyEntity);
        anotherCountryEntity.getNameTranslations().put(POLISH, NAME_TRANSLATION);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstCountryEntity, sameCountryEntityAsFirstCountryEntity);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstCountryEntity, anotherCountryEntity);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstCountryEntity.hashCode(), sameCountryEntityAsFirstCountryEntity.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstCountryEntity.hashCode(), anotherCountryEntity.hashCode());
    }
}