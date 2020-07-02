package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.twojprzelot.backend.domain.exception.ImportException;

@Slf4j
@RequiredArgsConstructor
public class ImportStaticData {
    private final ImportCurrency importCurrency;
    private final ImportCountry importCountry;
    private final ImportCity importCity;
    private final ImportAirport importAirport;

    public void overrideAll() {
        log.info("Overriding Static Data");

        overrideAllCurrencies();
        overrideAllCountries();
        overrideAllCities();
        overrideAllAirports();

        log.info("Overridden Static Data");
    }

    private void overrideAllCurrencies() {
        try {
            importCurrency.overrideAll();
        } catch (ImportException importException) {
            log.error(importException.getMessage());
        }
    }

    private void overrideAllCountries() {
        try {
            importCountry.overrideAll();
        } catch (ImportException importException) {
            log.error(importException.getMessage());
        }
    }

    private void overrideAllCities() {
        try {
            importCity.overrideAll();
        } catch (ImportException importException) {
            log.error(importException.getMessage());
        }
    }

    private void overrideAllAirports() {
        try {
            importAirport.overrideAll();
        } catch (ImportException importException) {
            log.error(importException.getMessage());
        }
    }
}
