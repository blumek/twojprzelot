package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Currency;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyDatabaseRepositoryTest {
    private static final String CODE = "CODE";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private CurrencyDatabaseRepository currencyDatabaseRepository;
    @Mock
    private CurrencySpringRepository currencySpringRepository;

    private CurrencyEntity currencyEntity;
    private CurrencyEntity anotherCurrencyEntity;
    private Currency currency;
    private Currency anotherCurrency;

    @BeforeEach
    void setUp() {
        currencyEntity = new CurrencyEntity();
        currencyEntity.setId(ID);
        currencyEntity.setCode(CODE);

        anotherCurrencyEntity = new CurrencyEntity();
        anotherCurrencyEntity.setId(ANOTHER_ID);
        anotherCurrencyEntity.setCode(CODE);

        currency = Currency.builder()
                .id(ID)
                .code(CODE)
                .build();

        anotherCurrency = Currency.builder()
                .id(ANOTHER_ID)
                .code(CODE)
                .build();
    }

    @Test
    void findAllTest_noCurrenciesAvailable() {
        when(currencySpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<Currency> foundCurrencies = currencyDatabaseRepository.findAll();
        assertTrue(foundCurrencies.isEmpty());

        verify(currencySpringRepository).findAll();
    }

    @Test
    void findAllTest_twoCurrenciesAvailable() {
        when(currencySpringRepository.findAll())
                .thenReturn(Lists.newArrayList(currencyEntity, anotherCurrencyEntity));

        List<Currency> foundCurrencies = currencyDatabaseRepository.findAll();
        assertThat(foundCurrencies, containsInAnyOrder(currency, anotherCurrency));

        verify(currencySpringRepository).findAll();
    }

    @Test
    void findByCodeTest_currencyWithGivenCodeNotExists() {
        when(currencySpringRepository.findByCode(CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), currencyDatabaseRepository.findByCode(CODE));

        verify(currencySpringRepository).findByCode(CODE);
    }

    @Test
    void findByCodeTest_currencyWithGivenCodeExists() {
        when(currencySpringRepository.findByCode(CODE))
                .thenReturn(Optional.of(currencyEntity));

        assertEquals(Optional.of(currency), currencyDatabaseRepository.findByCode(CODE));

        verify(currencySpringRepository).findByCode(CODE);
    }

    @Test
    void findByCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> currencyDatabaseRepository.findByCode(null));

        verify(currencySpringRepository, never()).findByCode(null);
    }

    @Test
    void saveTest() {
        when(currencySpringRepository.save(currencyEntity))
                .thenReturn(currencyEntity);

        Currency saveCurrency = currencyDatabaseRepository.save(currency);
        assertEquals(currency, saveCurrency);

        verify(currencySpringRepository).save(currencyEntity);
    }

    @Test
    void saveTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> currencyDatabaseRepository.save(null));

        verify(currencySpringRepository, never()).save(null);
    }
}