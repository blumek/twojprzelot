package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.CountryImmutableRepository;
import pl.twojprzelot.backend.domain.port.CountryMutableRepository;
import pl.twojprzelot.backend.domain.port.CurrencyImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public final class ImportCountry {
    private final CountryImmutableRepository sourceRepository;
    private final CountryMutableRepository targetRepository;
    private final CurrencyImmutableRepository currencyImmutableRepository;

    public void importAll() {
        sourceRepository.findAll()
                .stream()
                .filter(this::hasIso2Code)
                .forEach(importedCountry -> {
                    Optional<Country> alreadyCreatedCountry =
                            targetRepository.findByIso2Code(importedCountry.getIso2Code());

                    if (alreadyCreatedCountry.isPresent())
                        updateCountry(importedCountry, alreadyCreatedCountry.get());
                    else
                        createCountry(importedCountry);
                });
    }

    private boolean hasIso2Code(Country country) {
        return country.getIso2Code() != null && !country.getIso2Code().isBlank();
    }

    private void updateCountry(Country country, Country alreadyCreatedCountry) {
        Country countryToUpdate = getCountryWithAssociation(country);
        countryToUpdate = getCountryWithId(countryToUpdate, alreadyCreatedCountry.getId());
        targetRepository.update(countryToUpdate);
    }

    private Country getCountryWithAssociation(Country country) {
        Currency currency = country.getCurrency();
        if (hasCode(currency)) {
            Optional<Currency> foundCurrency = currencyImmutableRepository.findByCode(currency.getCode());
            if (foundCurrency.isPresent())
                return getCountryWithCurrency(country, foundCurrency.get());
        }

        if (hasIsoNumber(currency)) {
            Optional<Currency> foundCurrency = currencyImmutableRepository.findByIsoNumber(currency.getIsoNumber());
            if (foundCurrency.isPresent())
                return getCountryWithCurrency(country, foundCurrency.get());
        }

        return getCountryWithoutCurrency(country);
    }

    private boolean hasCode(Currency currency) {
        return currency != null && currency.getCode() != null;
    }

    private Country getCountryWithCurrency(Country country, Currency currency) {
        return country.toBuilder()
                .currency(currency)
                .build();
    }

    private boolean hasIsoNumber(Currency currency) {
        return currency != null && currency.getIsoNumber() != 0;
    }

    private Country getCountryWithoutCurrency(Country country) {
        return country.toBuilder()
                .currency(null)
                .build();
    }

    private Country getCountryWithId(Country country, int id) {
        return country.toBuilder()
                .id(id)
                .build();
    }

    private void createCountry(Country country) {
        Country countryToCreate = getCountryWithAssociation(country);
        countryToCreate = getCountryWithoutId(countryToCreate);
        targetRepository.create(countryToCreate);
    }

    private Country getCountryWithoutId(Country country) {
        return country.toBuilder()
                .id(0)
                .build();
    }

    public void overrideAll() {
        List<Country> importedCountries = sourceRepository.findAll();
        if (importedCountries.isEmpty())
            throw new ImportException("No countries to import");

        List<Country> countriesToCreate = importedCountries.stream()
                .map(importedCountry -> {
                    Country countryToCreate = getCountryWithAssociation(importedCountry);
                    countryToCreate = getCountryWithoutId(countryToCreate);
                    return countryToCreate;
                })
                .collect(toList());
        targetRepository.overrideAll(countriesToCreate);
    }
}
