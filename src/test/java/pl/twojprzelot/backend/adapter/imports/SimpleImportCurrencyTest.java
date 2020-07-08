package pl.twojprzelot.backend.adapter.imports;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.CurrencyImmutableRepository;
import pl.twojprzelot.backend.domain.port.CurrencyMutableRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleImportCurrencyTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String CODE = "CODE";
    private static final String NAME = "NAME";

    private SimpleImportCurrency simpleImportCurrency;
    @Mock
    private CurrencyImmutableRepository sourceRepository;
    @Mock
    private CurrencyMutableRepository targetRepository;

    @BeforeEach
    void setUp() {
        simpleImportCurrency = new SimpleImportCurrency(sourceRepository, targetRepository);
    }

    @Test
    void importAllTest_noCurrenciesToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        simpleImportCurrency.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).findByCode(anyString());
        verify(targetRepository, never()).update(any());
        verify(targetRepository, never()).create(any());
    }

    @Test
    void importAllTest_oneCurrencyWithoutCode() {
        Currency currencyWithoutCode = Currency.builder()
                .id(ID)
                .name(NAME)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(currencyWithoutCode));

        simpleImportCurrency.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).findByCode(anyString());
        verify(targetRepository, never()).update(any());
        verify(targetRepository, never()).create(any());
    }

    @Test
    void importAllTest_oneNoExistingCurrency() {
        Currency currency = Currency.builder()
                .id(ID)
                .name(NAME)
                .code(CODE)
                .build();

        Currency currencyToCreate = removeCurrencyId(currency);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(currency));

        when(targetRepository.findByCode(CODE))
                .thenReturn(Optional.empty());

        simpleImportCurrency.importAll();

        verify(targetRepository).findByCode(CODE);
        verify(targetRepository, never()).update(any());
        verify(targetRepository).create(currencyToCreate);
    }

    private Currency removeCurrencyId(Currency currency) {
        return currency.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void importAllTest_oneExistingCurrency() {
        Currency currency = Currency.builder()
                .id(ID)
                .name(NAME)
                .code(CODE)
                .build();

        Currency alreadySavedCurrency = Currency.builder()
                .id(ANOTHER_ID)
                .code(CODE)
                .build();

        Currency currencyToUpdate = currency.toBuilder()
                .id(ANOTHER_ID)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(currency));

        when(targetRepository.findByCode(CODE))
                .thenReturn(Optional.of(alreadySavedCurrency));

        simpleImportCurrency.importAll();

        verify(targetRepository).findByCode(CODE);
        verify(targetRepository).update(currencyToUpdate);
        verify(targetRepository, never()).create(any());
    }

    @Test
    void overrideAllTest_noCurrenciesToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ImportException.class, () -> simpleImportCurrency.overrideAll());

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).overrideAll(anyList());
    }

    @Test
    void overrideAllTest_oneCurrencyToImport() {
        Currency currency = Currency.builder()
                .id(ID)
                .name(NAME)
                .code(CODE)
                .build();

        Currency currencyToCreate = removeCurrencyId(currency);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(currency));

        simpleImportCurrency.overrideAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).overrideAll(Lists.newArrayList(currencyToCreate));
    }
}