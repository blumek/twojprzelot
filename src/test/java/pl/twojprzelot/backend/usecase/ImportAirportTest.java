package pl.twojprzelot.backend.usecase;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.AirportImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirportMutableRepository;
import pl.twojprzelot.backend.domain.port.CityImmutableRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportAirportTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String AIRPORT_NAME = "NAME";
    private static final String CITY_IATA_CODE = "CITY_IATA_CODE";
    private static final String CITY_NAME = "CITY_NAME";

    private ImportAirport importAirport;
    @Mock
    private AirportImmutableRepository sourceRepository;
    @Mock
    private AirportMutableRepository targetRepository;
    @Mock
    private CityImmutableRepository cityRepository;

    @BeforeEach
    void setUp() {
        importAirport = new ImportAirport(sourceRepository, targetRepository, cityRepository);
    }

    @Test
    void importAllTest_noAirportsToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        importAirport.importAll();

        verify(sourceRepository).findAll();
        verify(sourceRepository, never()).findByIataCode(anyString());
        verify(targetRepository, never()).create(any());
        verify(targetRepository, never()).update(any());
        verify(cityRepository, never()).findByIataCode(anyString());
    }

    @Test
    void importAllTest_oneAirportWithoutIataCode() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(AIRPORT_NAME)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airport));

        importAirport.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).findByIataCode(IATA_CODE);
        verify(targetRepository, never()).create(any());
        verify(targetRepository, never()).update(any());
        verify(cityRepository, never()).findByIataCode(anyString());
    }

    @Test
    void importAllTest_oneNoExistingAirport_WithCityIataCode_CityExists() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(AIRPORT_NAME)
                .iataCode(IATA_CODE)
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .build())
                .build();

        City alreadySavedCity = City.builder()
                .name(CITY_NAME)
                .iataCode(CITY_IATA_CODE)
                .build();

        Airport airportToCreate = airport.toBuilder()
                .city(alreadySavedCity)
                .build();

        airportToCreate = removeAirportId(airportToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airport));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        when(cityRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.of(alreadySavedCity));

        importAirport.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository).create(airportToCreate);
        verify(targetRepository, never()).update(any());
        verify(cityRepository).findByIataCode(CITY_IATA_CODE);
    }

    private Airport removeAirportId(Airport airport) {
        return airport.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void importAllTest_oneNoExistingAirport_WithCityIataCode_CityNotExists() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(AIRPORT_NAME)
                .iataCode(IATA_CODE)
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .build())
                .build();

        Airport airportToCreate = airport.toBuilder()
                .city(null)
                .build();

        airportToCreate = removeAirportId(airportToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airport));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        when(cityRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.empty());

        importAirport.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository).create(airportToCreate);
        verify(targetRepository, never()).update(any());
        verify(cityRepository).findByIataCode(CITY_IATA_CODE);
    }

    @Test
    void importAllTest_oneAlreadyExistingAirport_WithCityIataCode_CityExists() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(AIRPORT_NAME)
                .iataCode(IATA_CODE)
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .build())
                .build();

        City alreadySavedCity = City.builder()
                .name(CITY_NAME)
                .iataCode(CITY_IATA_CODE)
                .build();

        Airport alreadySavedAirport = airport.toBuilder()
                .id(ANOTHER_ID)
                .build();

        Airport airportToUpdate = alreadySavedAirport.toBuilder()
                .city(alreadySavedCity)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airport));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(alreadySavedAirport));

        when(cityRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.of(alreadySavedCity));

        importAirport.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(airportToUpdate);
        verify(cityRepository).findByIataCode(CITY_IATA_CODE);
    }

    @Test
    void importAllTest_oneAlreadyExistingAirport_WithCityIataCode_CityNotExists() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(AIRPORT_NAME)
                .iataCode(IATA_CODE)
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .build())
                .build();

        Airport alreadySavedAirport = airport.toBuilder()
                .id(ANOTHER_ID)
                .build();

        Airport airportToUpdate = alreadySavedAirport.toBuilder()
                .city(null)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airport));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(alreadySavedAirport));

        when(cityRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.empty());

        importAirport.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(airportToUpdate);
        verify(cityRepository).findByIataCode(CITY_IATA_CODE);
    }

    @Test
    void overrideAllTest_noAirportsToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ImportException.class, () -> importAirport.overrideAll());

        verify(sourceRepository).findAll();
        verify(cityRepository, never()).findByIataCode(anyString());
        verify(targetRepository, never()).overrideAll(anyList());
    }

    @Test
    void overrideAllTest_oneAirport_WithCityIataCode_CityExists() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(AIRPORT_NAME)
                .iataCode(IATA_CODE)
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .build())
                .build();

        City alreadySavedCity = City.builder()
                .name(CITY_NAME)
                .iataCode(CITY_IATA_CODE)
                .build();

        Airport airportToCreate = airport.toBuilder()
                .city(alreadySavedCity)
                .build();

        airportToCreate = removeAirportId(airportToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airport));

        when(cityRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.of(alreadySavedCity));

        importAirport.overrideAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).overrideAll(Lists.newArrayList(airportToCreate));
        verify(cityRepository).findByIataCode(CITY_IATA_CODE);
    }

    @Test
    void overrideAllTest_oneAirport_WithCityIataCode_CityNotExists() {
        Airport airport = Airport.builder()
                .id(ID)
                .name(AIRPORT_NAME)
                .iataCode(IATA_CODE)
                .city(City.builder()
                        .iataCode(CITY_IATA_CODE)
                        .build())
                .build();

        Airport airportToCreate = airport.toBuilder()
                .city(null)
                .build();

        airportToCreate = removeAirportId(airportToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airport));

        when(cityRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.empty());

        importAirport.overrideAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).overrideAll(Lists.newArrayList(airportToCreate));
        verify(cityRepository).findByIataCode(CITY_IATA_CODE);
    }
}