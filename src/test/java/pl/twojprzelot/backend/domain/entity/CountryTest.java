package pl.twojprzelot.backend.domain.entity;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static pl.twojprzelot.backend.domain.entity.Language.POLISH;

class CountryTest {
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String ANOTHER_NAME = "ANOTHER_NAME";
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final String ISO_3_CODE = "ISO_3_CODE";
    private static final int ISO_NUMBER = 1;
    private static final int POPULATION = 10;
    private static final int ANOTHER_POPULATION = 20;
    private static final String CURRENCY_NAME = "CURRENCY_NAME";
    private static final String NAME_TRANSLATION = "NAME_TRANSLATION";

    private Country firstCountry;
    private Country sameCountryAsFirstCountry;
    private Country anotherCountry;

    private Map<Language, String> nameTranslations;

    @BeforeEach
    void setUp() {
        nameTranslations = Maps.newHashMap();

        firstCountry = Country.builder()
                .id(ID)
                .name(NAME)
                .iso2Code(ISO_2_CODE)
                .iso3Code(ISO_3_CODE)
                .isoNumber(ISO_NUMBER)
                .population(POPULATION)
                .nameTranslations(nameTranslations)
                .build();

        sameCountryAsFirstCountry = Country.builder()
                .id(ID)
                .name(NAME)
                .iso2Code(ISO_2_CODE)
                .iso3Code(ISO_3_CODE)
                .isoNumber(ISO_NUMBER)
                .population(POPULATION)
                .nameTranslations(nameTranslations)
                .build();

        anotherCountry = Country.builder()
                .id(ID)
                .name(ANOTHER_NAME)
                .iso2Code(ISO_2_CODE)
                .iso3Code(ISO_3_CODE)
                .isoNumber(ISO_NUMBER)
                .population(ANOTHER_POPULATION)
                .nameTranslations(nameTranslations)
                .build();
    }

    @Test
    void builderTest_id() {
        Country country = Country.builder()
                .id(ID)
                .build();

        assertEquals(ID, country.getId());
    }

    @Test
    void builderTest_name() {
        Country country = Country.builder()
                .name(NAME)
                .build();

        assertEquals(NAME, country.getName());
    }

    @Test
    void builderTest_iso2Code() {
        Country country = Country.builder()
                .iso2Code(ISO_2_CODE)
                .build();

        assertEquals(ISO_2_CODE, country.getIso2Code());
    }

    @Test
    void builderTest_iso3Code() {
        Country country = Country.builder()
                .iso3Code(ISO_3_CODE)
                .build();

        assertEquals(ISO_3_CODE, country.getIso3Code());
    }

    @Test
    void builderTest_isoNumber() {
        Country country = Country.builder()
                .isoNumber(ISO_NUMBER)
                .build();

        assertEquals(ISO_NUMBER, country.getIsoNumber());
    }

    @Test
    void builderTest_population() {
        Country country = Country.builder()
                .population(POPULATION)
                .build();

        assertEquals(POPULATION, country.getPopulation());
    }

    @Test
    void builderTest_currency() {
        Currency currency = Currency.builder()
                .name(CURRENCY_NAME)
                .build();

        Country country = Country.builder()
                .currency(currency)
                .build();

        assertEquals(currency, country.getCurrency());
    }

    @Test
    void builderTest_nameTranslations() {
        nameTranslations.put(POLISH, NAME_TRANSLATION);

        Country country = Country.builder()
                .nameTranslations(nameTranslations)
                .build();

        assertThat(country.getNameTranslations(), hasEntry(POLISH, NAME_TRANSLATION));
    }

    @Test
    void builderTest_addSingleNameTranslation() {
        Country country = Country.builder()
                .nameTranslation(POLISH, NAME_TRANSLATION)
                .build();

        assertThat(country.getNameTranslations(), hasEntry(POLISH, NAME_TRANSLATION));
    }

    @Test
    void builderTest_toBuilder() {
        Country country = Country.builder()
                .name(NAME)
                .iso2Code(ISO_2_CODE)
                .iso3Code(ISO_3_CODE)
                .isoNumber(ISO_NUMBER)
                .population(POPULATION)
                .build();

        Country anotherCountry = country.toBuilder()
                .name(ANOTHER_NAME)
                .population(ANOTHER_POPULATION)
                .build();

        Country expectedCountry = Country.builder()
                .name(ANOTHER_NAME)
                .iso2Code(ISO_2_CODE)
                .iso3Code(ISO_3_CODE)
                .isoNumber(ISO_NUMBER)
                .population(ANOTHER_POPULATION)
                .build();

        assertEquals(expectedCountry, anotherCountry);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstCountry, sameCountryAsFirstCountry);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstCountry, anotherCountry);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstCountry.hashCode(), sameCountryAsFirstCountry.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstCountry.hashCode(), anotherCountry.hashCode());
    }
}