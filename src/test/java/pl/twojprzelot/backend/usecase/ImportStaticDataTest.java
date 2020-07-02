package pl.twojprzelot.backend.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.exception.ImportException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportStaticDataTest {
    @InjectMocks
    private ImportStaticData importStaticData;
    @Mock
    private ImportCurrency importCurrency;
    @Mock
    private ImportCountry importCountry;
    @Mock
    private ImportCity importCity;
    @Mock
    private ImportAirport importAirport;

    @Test
    void overrideAllTest() {
        importStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInCurrencyImport() {
        doThrow(ImportException.class)
                .when(importCurrency).overrideAll();

        importStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInCountryImport() {
        doThrow(ImportException.class)
                .when(importCountry).overrideAll();

        importStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInCityImport() {
        doThrow(ImportException.class)
                .when(importCity).overrideAll();

        importStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInAirportImport() {
        doThrow(ImportException.class)
                .when(importAirport).overrideAll();

        importStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }
}