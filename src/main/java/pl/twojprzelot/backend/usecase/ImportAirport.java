package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.AirportImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirportMutableRepository;
import pl.twojprzelot.backend.domain.port.CityImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class ImportAirport {
    private final AirportImmutableRepository sourceRepository;
    private final AirportMutableRepository targetRepository;
    private final CityImmutableRepository cityRepository;

    public void importAll() {
        sourceRepository.findAll()
                .stream()
                .filter(this::hasIataCode)
                .forEach(importedAirport -> {
                    Optional<Airport> alreadySavedAirport = getAlreadySavedAirport(importedAirport);

                    if (alreadySavedAirport.isPresent())
                        updateAirport(importedAirport, alreadySavedAirport.get());
                    else
                        createAirport(importedAirport);
                });
    }

    private boolean hasIataCode(Airport airport) {
        return airport.getIataCode() != null;
    }

    private Optional<Airport> getAlreadySavedAirport(Airport importedAirport) {
        return targetRepository.findByIataCode(importedAirport.getIataCode());
    }

    private void updateAirport(Airport airport, Airport alreadySavedAirport) {
        Airport airportToUpdate = getAirportToUpdate(airport, alreadySavedAirport);
        targetRepository.update(airportToUpdate);
    }

    private Airport getAirportToUpdate(Airport airport, Airport alreadySavedAirport) {
        Airport airportToUpdate = getAirportWithAssociation(airport);
        airportToUpdate = getAirportWithId(airportToUpdate, alreadySavedAirport.getId());
        return airportToUpdate;
    }

    private Airport getAirportWithAssociation(Airport airport) {
        City city = airport.getCity();
        if (hasIataCode(city)) {
            Optional<City> foundCity = cityRepository.findByIataCode(city.getIataCode());
            if (foundCity.isPresent())
                return getAirportWithCity(airport, foundCity.get());
        }

        return getAirportWithoutCity(airport);
    }

    private boolean hasIataCode(City city) {
        return city.getIataCode() != null;
    }

    private Airport getAirportWithCity(Airport airport, City city) {
        return airport.toBuilder()
                .city(city)
                .build();
    }

    private Airport getAirportWithoutCity(Airport airport) {
        return airport.toBuilder()
                .city(null)
                .build();
    }

    private Airport getAirportWithId(Airport airport, int id) {
        return airport.toBuilder()
                .id(id)
                .build();
    }

    private void createAirport(Airport airport) {
        Airport airportToCreate = getAirportToCreate(airport);
        targetRepository.create(airportToCreate);
    }

    public Airport getAirportToCreate(Airport airport) {
        Airport airportToCreate = getAirportWithAssociation(airport);
        airportToCreate = getAirportWithoutId(airportToCreate);
        return airportToCreate;
    }

    private Airport getAirportWithoutId(Airport airport) {
        return airport.toBuilder()
                .id(0)
                .build();
    }

    public void overrideAll() {
        List<Airport> importedAirports = sourceRepository.findAll();
        if (importedAirports.isEmpty())
            throw new ImportException("No airports to import");

        List<Airport> airportsToCreate = importedAirports.stream()
                .map(this::getAirportToCreate)
                .collect(toList());

        targetRepository.overrideAll(airportsToCreate);
    }
}
