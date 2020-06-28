package pl.twojprzelot.backend.usecase;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.CountryImmutableRepository;
import pl.twojprzelot.backend.domain.port.CountryMutableRepository;
import pl.twojprzelot.backend.domain.port.CurrencyImmutableRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportCountryTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final int POPULATION = 10000;
    private static final String CURRENCY_CODE = "CURRENCY_CODE";
    private static final int CURRENCY_ISO_NUMBER = 123;
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final String CURRENCY_NAME = "CURRENCY_NAME";

    private ImportCountry importCountry;
    @Mock
    private CountryImmutableRepository sourceRepository;
    @Mock
    private CountryMutableRepository targetRepository;
    @Mock
    private CurrencyImmutableRepository currencyRepository;

    @BeforeEach
    void setUp() {
        importCountry = new ImportCountry(sourceRepository, targetRepository, currencyRepository);
    }

    @Test
    void importAllTest_noCountriesToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).findByIso2Code(anyString());
        verify(targetRepository, never()).create(any());
        verify(targetRepository, never()).update(any());
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
    }

    @Test
    void importAllTest_oneCountryWithoutIso2Code() {
        Country country = Country.builder()
                .id(ID)
                .population(POPULATION)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).findByIso2Code(anyString());
        verify(targetRepository, never()).create(any());
        verify(targetRepository, never()).update(any());
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
    }

    @Test
    void importAllTest_oneNoExistingCountry_WithCurrencyCode_CurrencyExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .code(CURRENCY_CODE)
                        .build())
                .build();

        Currency alreadySavedCurrency = Currency.builder()
                .name(CURRENCY_NAME)
                .code(CURRENCY_CODE)
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(alreadySavedCurrency)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.empty());

        when(currencyRepository.findByCode(CURRENCY_CODE))
                .thenReturn(Optional.of(alreadySavedCurrency));

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIso2Code(ISO_2_CODE);
        verify(targetRepository).create(countryToCreate);
        verify(targetRepository, never()).update(any());
        verify(currencyRepository).findByCode(CURRENCY_CODE);
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
    }

    private Country removeCountryId(Country country) {
        return country.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void importAllTest_oneNoExistingCountry_WithCurrencyCode_CurrencyNotExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .code(CURRENCY_CODE)
                        .build())
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(null)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.empty());

        when(currencyRepository.findByCode(CURRENCY_CODE))
                .thenReturn(Optional.empty());

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIso2Code(ISO_2_CODE);
        verify(targetRepository).create(countryToCreate);
        verify(targetRepository, never()).update(any());
        verify(currencyRepository).findByCode(CURRENCY_CODE);
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
    }

    @Test
    void importAllTest_oneNoExistingCountry_WithCurrencyIsoNumber_CurrencyExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .isoNumber(CURRENCY_ISO_NUMBER)
                        .build())
                .build();

        Currency alreadySavedCurrency = Currency.builder()
                .name(CURRENCY_NAME)
                .isoNumber(CURRENCY_ISO_NUMBER)
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(alreadySavedCurrency)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.empty());

        when(currencyRepository.findByIsoNumber(CURRENCY_ISO_NUMBER))
                .thenReturn(Optional.of(alreadySavedCurrency));

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIso2Code(ISO_2_CODE);
        verify(targetRepository).create(countryToCreate);
        verify(targetRepository, never()).update(any());
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository).findByIsoNumber(CURRENCY_ISO_NUMBER);
    }

    @Test
    void importAllTest_oneNoExistingCountry_WithCurrencyIsoNumber_CurrencyNotExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .isoNumber(CURRENCY_ISO_NUMBER)
                        .build())
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(null)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.empty());

        when(currencyRepository.findByIsoNumber(CURRENCY_ISO_NUMBER))
                .thenReturn(Optional.empty());

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIso2Code(ISO_2_CODE);
        verify(targetRepository).create(countryToCreate);
        verify(targetRepository, never()).update(any());
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository).findByIsoNumber(CURRENCY_ISO_NUMBER);
    }

    @Test
    void importAllTest_oneAlreadyExistingCountry_WithCurrencyCode_CurrencyExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .code(CURRENCY_CODE)
                        .build())
                .build();

        Currency alreadySavedCurrency = Currency.builder()
                .name(CURRENCY_NAME)
                .code(CURRENCY_CODE)
                .build();

        Country alreadySavedCountry = country.toBuilder()
                .id(ANOTHER_ID)
                .build();

        Country countryToUpdate = alreadySavedCountry.toBuilder()
                .currency(alreadySavedCurrency)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.of(alreadySavedCountry));

        when(currencyRepository.findByCode(CURRENCY_CODE))
                .thenReturn(Optional.of(alreadySavedCurrency));

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(countryToUpdate);
        verify(currencyRepository).findByCode(CURRENCY_CODE);
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
    }

    @Test
    void importAllTest_oneAlreadyExistingCountry_WithCurrencyCode_CurrencyNotExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .code(CURRENCY_CODE)
                        .build())
                .build();

        Country alreadySavedCountry = country.toBuilder()
                .id(ANOTHER_ID)
                .build();

        Country countryToUpdate = alreadySavedCountry.toBuilder()
                .currency(null)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.of(alreadySavedCountry));

        when(currencyRepository.findByCode(CURRENCY_CODE))
                .thenReturn(Optional.empty());

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(countryToUpdate);
        verify(currencyRepository).findByCode(CURRENCY_CODE);
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
    }

    @Test
    void importAllTest_oneAlreadyExistingCountry_WithCurrencyIsoNumber_CurrencyExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .isoNumber(CURRENCY_ISO_NUMBER)
                        .build())
                .build();

        Country alreadySavedCountry = country.toBuilder()
                .id(ANOTHER_ID)
                .build();

        Currency alreadySavedCurrency = Currency.builder()
                .name(CURRENCY_NAME)
                .isoNumber(CURRENCY_ISO_NUMBER)
                .build();

        Country countryToUpdate = alreadySavedCountry.toBuilder()
                .currency(alreadySavedCurrency)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.of(alreadySavedCountry));

        when(currencyRepository.findByIsoNumber(CURRENCY_ISO_NUMBER))
                .thenReturn(Optional.of(alreadySavedCurrency));

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(countryToUpdate);
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository).findByIsoNumber(CURRENCY_ISO_NUMBER);
    }

    @Test
    void importAllTest_oneAlreadyExistingCountry_WithCurrencyIsoNumber_CurrencyNotExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .isoNumber(CURRENCY_ISO_NUMBER)
                        .build())
                .build();

        Country alreadySavedCountry = country.toBuilder()
                .id(ANOTHER_ID)
                .build();

        Country countryToUpdate = alreadySavedCountry.toBuilder()
                .currency(null)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(targetRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.of(alreadySavedCountry));

        when(currencyRepository.findByIsoNumber(CURRENCY_ISO_NUMBER))
                .thenReturn(Optional.empty());

        importCountry.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(countryToUpdate);
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository).findByIsoNumber(CURRENCY_ISO_NUMBER);
    }

    @Test
    void overrideAllTest_noCountriesToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ImportException.class, () -> importCountry.overrideAll());

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).overrideAll(anyList());
    }

    @Test
    void overrideAllTest_oneCountry_WithCurrencyCode_CurrencyExists() {
        Country country = Country.builder()
                .id(ID)
                .population(POPULATION)
                .currency(Currency.builder()
                        .code(CURRENCY_CODE)
                        .build())
                .build();

        Currency alreadySavedCurrency = Currency.builder()
                .name(CURRENCY_NAME)
                .code(CURRENCY_CODE)
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(alreadySavedCurrency)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(currencyRepository.findByCode(CURRENCY_CODE))
                .thenReturn(Optional.of(alreadySavedCurrency));

        importCountry.overrideAll();

        verify(sourceRepository).findAll();
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
        verify(currencyRepository).findByCode(CURRENCY_CODE);
        verify(targetRepository).overrideAll(Lists.newArrayList(countryToCreate));
    }

    @Test
    void overrideAllTest_oneCountry_WithCurrencyCode_CurrencyNotExists() {
        Country country = Country.builder()
                .id(ID)
                .population(POPULATION)
                .currency(Currency.builder()
                        .code(CURRENCY_CODE)
                        .build())
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(null)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(currencyRepository.findByCode(CURRENCY_CODE))
                .thenReturn(Optional.empty());

        importCountry.overrideAll();

        verify(sourceRepository).findAll();
        verify(currencyRepository, never()).findByIsoNumber(anyInt());
        verify(currencyRepository).findByCode(CURRENCY_CODE);
        verify(targetRepository).overrideAll(Lists.newArrayList(countryToCreate));
    }

    @Test
    void overrideAllTest_oneCountry_WithCurrencyIsoNumber_CurrencyExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .isoNumber(CURRENCY_ISO_NUMBER)
                        .build())
                .build();

        Currency alreadySavedCurrency = Currency.builder()
                .name(CURRENCY_NAME)
                .isoNumber(CURRENCY_ISO_NUMBER)
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(alreadySavedCurrency)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(currencyRepository.findByIsoNumber(CURRENCY_ISO_NUMBER))
                .thenReturn(Optional.of(alreadySavedCurrency));

        importCountry.overrideAll();

        verify(sourceRepository).findAll();
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository).findByIsoNumber(CURRENCY_ISO_NUMBER);
        verify(targetRepository).overrideAll(Lists.newArrayList(countryToCreate));
    }

    @Test
    void overrideAllTest_oneCountry_WithCurrencyIsoNumber_CurrencyNotExists() {
        Country country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .population(POPULATION)
                .currency(Currency.builder()
                        .isoNumber(CURRENCY_ISO_NUMBER)
                        .build())
                .build();

        Country countryToCreate = country.toBuilder()
                .currency(null)
                .build();

        countryToCreate = removeCountryId(countryToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(country));

        when(currencyRepository.findByIsoNumber(CURRENCY_ISO_NUMBER))
                .thenReturn(Optional.empty());

        importCountry.overrideAll();

        verify(sourceRepository).findAll();
        verify(currencyRepository, never()).findByCode(anyString());
        verify(currencyRepository).findByIsoNumber(CURRENCY_ISO_NUMBER);
        verify(targetRepository).overrideAll(Lists.newArrayList(countryToCreate));
    }
}