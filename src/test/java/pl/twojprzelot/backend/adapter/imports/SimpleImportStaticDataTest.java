package pl.twojprzelot.backend.adapter.imports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleImportStaticDataTest {
    @InjectMocks
    private SimpleImportStaticData simpleImportStaticData;
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
        doNothing()
                .when(importCurrency).overrideAll();

        doNothing()
                .when(importCountry).overrideAll();

        doNothing()
                .when(importCity).overrideAll();

        doNothing()
                .when(importAirport).overrideAll();

        simpleImportStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInCurrencyImport() {
        doThrow(ImportException.class)
                .when(importCurrency).overrideAll();

        doNothing()
                .when(importCountry).overrideAll();

        doNothing()
                .when(importCity).overrideAll();

        doNothing()
                .when(importAirport).overrideAll();

        simpleImportStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInCountryImport() {
        doNothing()
                .when(importCurrency).overrideAll();

        doThrow(ImportException.class)
                .when(importCountry).overrideAll();

        doNothing()
                .when(importCity).overrideAll();

        doNothing()
                .when(importAirport).overrideAll();

        simpleImportStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInCityImport() {
        doNothing()
                .when(importCurrency).overrideAll();

        doNothing()
                .when(importCountry).overrideAll();

        doThrow(ImportException.class)
                .when(importCity).overrideAll();

        doNothing()
                .when(importAirport).overrideAll();

        simpleImportStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }

    @Test
    void overrideAllTest_importExceptionInAirportImport() {
        doNothing()
                .when(importCurrency).overrideAll();

        doNothing()
                .when(importCountry).overrideAll();

        doNothing()
                .when(importCity).overrideAll();

        doThrow(ImportException.class)
                .when(importAirport).overrideAll();

        simpleImportStaticData.overrideAll();

        verify(importCurrency).overrideAll();
        verify(importCountry).overrideAll();
        verify(importCity).overrideAll();
        verify(importAirport).overrideAll();
    }
}