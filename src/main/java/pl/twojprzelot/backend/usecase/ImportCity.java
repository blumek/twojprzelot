package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.CityImmutableRepository;
import pl.twojprzelot.backend.domain.port.CityMutableRepository;
import pl.twojprzelot.backend.domain.port.CountryImmutableRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public final class ImportCity {
    private final CityImmutableRepository sourceRepository;
    private final CityMutableRepository targetRepository;
    private final CountryImmutableRepository countryRepository;

    public void importAll() {
        sourceRepository.findAll()
                .stream()
                .filter(this::hasIataCode)
                .forEach(importedCity -> {
                    Optional<City> alreadyCreatedCity = targetRepository.findByIataCode(importedCity.getIataCode());

                    if (alreadyCreatedCity.isPresent())
                        updateCity(importedCity, alreadyCreatedCity.get());
                    else
                        createCity(importedCity);
                });
    }

    private boolean hasIataCode(City city) {
        return city.getIataCode() != null;
    }

    private void updateCity(City city, City alreadyCreatedCity) {
        City cityToUpdate = getCityWithAssociation(city);
        cityToUpdate = getCityWithId(cityToUpdate, alreadyCreatedCity.getId());
        targetRepository.update(cityToUpdate);
    }

    private City getCityWithAssociation(City city) {
        Country country = city.getCountry();
        if (hasIso2Code(country)) {
            Optional<Country> foundCountry = countryRepository.findByIso2Code(country.getIso2Code());
            if (foundCountry.isPresent())
                return getCityWithCountry(city, foundCountry.get());
        }

        return getCityWithoutCountry(city);
    }

    private boolean hasIso2Code(Country country) {
        return country != null && country.getIso2Code() != null;
    }

    private City getCityWithCountry(City city, Country country) {
        return city.toBuilder()
                .country(country)
                .build();
    }

    private City getCityWithoutCountry(City city) {
        return city.toBuilder()
                .country(null)
                .build();
    }

    private City getCityWithId(City city, int id) {
        return city.toBuilder()
                .id(id)
                .build();
    }

    private void createCity(City city) {
        City cityToCreate = getCityWithAssociation(city);
        cityToCreate = getCityWithoutId(cityToCreate);
        targetRepository.create(cityToCreate);
    }

    private City getCityWithoutId(City city) {
        return city.toBuilder()
                .id(0)
                .build();
    }

    public void overrideAll() {
        List<City> importedCities = sourceRepository.findAll();
        if (importedCities.isEmpty())
            throw new ImportException("No cities to import");

        List<City> citiesToCreate = importedCities.stream()
                .map(importedCity -> {
                    City cityToCreate = getCityWithAssociation(importedCity);
                    cityToCreate = getCityWithoutId(cityToCreate);
                    return cityToCreate;
                })
                .collect(toList());
        targetRepository.overrideAll(citiesToCreate);
    }
}
