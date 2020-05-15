package pl.twojprzelot.backend.usecase;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.port.CurrencyImmutableRepository;
import pl.twojprzelot.backend.domain.port.CurrencyMutableRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportCurrencyTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String CODE = "CODE";
    private static final String ANOTHER_CODE = "ANOTHER_CODE";
    protected static final int OLD_ID = 3;

    private ImportCurrency importCurrency;
    @Mock
    private CurrencyImmutableRepository sourceRepository;
    @Mock
    private CurrencyMutableRepository targetRepository;

    @BeforeEach
    void setUp() {
        importCurrency = new ImportCurrency(sourceRepository, targetRepository);
    }

    @Test
    void importCurrencyTest_noCurrenciesFromSourceRepository() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        importCurrency.importAll();

        verify(targetRepository, never()).findByCode(anyString());
        verify(targetRepository, never()).update(any());
        verify(targetRepository, never()).create(any());
    }

    @Test
    void importCurrencyTest_onlyCurrencyWithoutCode() {
        Currency currencyWithoutCode = Currency.builder()
                .id(ID)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(currencyWithoutCode));

        importCurrency.importAll();

        verify(targetRepository, never()).findByCode(anyString());
        verify(targetRepository, never()).update(any());
        verify(targetRepository, never()).create(any());
    }

    @Test
    void importCurrencyTest_currencyNotExistsInTargetRepository() {
        Currency currency = Currency.builder()
                .id(ID)
                .code(CODE)
                .build();

        Currency currencyWithoutId = Currency.builder()
                .code(CODE)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(currency));

        when(targetRepository.findByCode(CODE))
                .thenReturn(Optional.empty());

        importCurrency.importAll();

        verify(targetRepository).findByCode(CODE);
        verify(targetRepository, never()).update(any());
        verify(targetRepository).create(currencyWithoutId);
    }

    @Test
    void importCurrencyTest_currencyExistsInTargetRepository() {
        Currency currency = Currency.builder()
                .code(CODE)
                .build();

        Currency foundCurrency = Currency.builder()
                .id(ID)
                .code(CODE)
                .build();

        Currency currencyWithId = currency.toBuilder()
                .id(ID)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(currency));

        when(targetRepository.findByCode(CODE))
                .thenReturn(Optional.of(foundCurrency));

        importCurrency.importAll();

        verify(targetRepository).findByCode(CODE);
        verify(targetRepository).update(currencyWithId);
        verify(targetRepository, never()).create(any());
    }

    @Test
    void importCurrencyTest_twoCurrenciesFromSourceRepository() {
        Currency firstCurrency = Currency.builder()
                .id(OLD_ID)
                .code(CODE)
                .build();

        Currency secondCurrency = Currency.builder()
                .id(OLD_ID)
                .code(ANOTHER_CODE)
                .build();

        Currency foundCurrency = Currency.builder()
                .id(ID)
                .code(CODE)
                .build();

        Currency currencyWithId = firstCurrency.toBuilder()
                .id(ID)
                .build();

        Currency currencyWithoutId = secondCurrency.toBuilder()
                .id(0)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(firstCurrency, secondCurrency));

        when(targetRepository.findByCode(CODE))
                .thenReturn(Optional.of(foundCurrency));

        when(targetRepository.findByCode(ANOTHER_CODE))
                .thenReturn(Optional.empty());

        importCurrency.importAll();

        verify(targetRepository).findByCode(CODE);
        verify(targetRepository).findByCode(ANOTHER_CODE);
        verify(targetRepository).update(currencyWithId);
        verify(targetRepository).create(currencyWithoutId);
    }
}