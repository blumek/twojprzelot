package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Currency;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyDatabaseRepositoryTest {
    private static final String CODE = "CODE";
    private static final int ID = 1;

    @InjectMocks
    private CurrencyDatabaseRepository currencyDatabaseRepository;
    @Mock
    private CurrencySpringRepository currencySpringRepository;

    @Test
    void findByCodeTest_currencyWithGivenCodeNotExists() {
        when(currencySpringRepository.findByCode(CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), currencyDatabaseRepository.findByCode(CODE));
    }

    @Test
    void findByCodeTest_currencyWithGivenCodeExists() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(ID);
        currencyEntity.setCode(CODE);

        when(currencySpringRepository.findByCode(CODE))
                .thenReturn(Optional.of(currencyEntity));

        Currency currency = Currency.builder()
                .id(ID)
                .code(CODE)
                .build();

        assertEquals(Optional.of(currency), currencyDatabaseRepository.findByCode(CODE));
    }
}