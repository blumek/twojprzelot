package pl.twojprzelot.backend.adapter.imports;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.CityImmutableRepository;
import pl.twojprzelot.backend.domain.port.CityMutableRepository;
import pl.twojprzelot.backend.domain.port.CountryImmutableRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleImportCityTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String CITY_NAME = "NAME";
    private static final String COUNTRY_ISO_2_CODE = "COUNTRY_ISO_2_CODE";
    private static final String COUNTRY_NAME = "COUNTRY_NAME";

    private SimpleImportCity simpleImportCity;
    @Mock
    private CityImmutableRepository sourceRepository;
    @Mock
    private CityMutableRepository targetRepository;
    @Mock
    private CountryImmutableRepository countryRepository;

    @BeforeEach
    void setUp() {
        simpleImportCity = new SimpleImportCity(sourceRepository, targetRepository, countryRepository);
    }

    @Test
    void importAllTest_noCitiesToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        simpleImportCity.importAll();

        verify(sourceRepository).findAll();
        verify(sourceRepository, never()).findByIataCode(anyString());
        verify(targetRepository, never()).create(any());
        verify(targetRepository, never()).update(any());
        verify(countryRepository, never()).findByIso2Code(anyString());
    }

    @Test
    void importAllTest_oneCityWithoutIataCode() {
        City city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(city));

        simpleImportCity.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).findByIataCode(IATA_CODE);
        verify(targetRepository, never()).create(any());
        verify(targetRepository, never()).update(any());
        verify(countryRepository, never()).findByIso2Code(anyString());
    }

    @Test
    void importAllTest_oneNoExistingCity_WithCountryIso2Code_CountryExists() {
        City city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .iataCode(IATA_CODE)
                .country(Country.builder()
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .build())
                .build();

        Country alreadySavedCountry = Country.builder()
                .name(COUNTRY_NAME)
                .iso2Code(COUNTRY_ISO_2_CODE)
                .build();

        City cityToCreate = city.toBuilder()
                .country(alreadySavedCountry)
                .build();

        cityToCreate = removeCityId(cityToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(city));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        when(countryRepository.findByIso2Code(COUNTRY_ISO_2_CODE))
                .thenReturn(Optional.of(alreadySavedCountry));

        simpleImportCity.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository).create(cityToCreate);
        verify(targetRepository, never()).update(any());
        verify(countryRepository).findByIso2Code(COUNTRY_ISO_2_CODE);
    }

    private City removeCityId(City city) {
        return city.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void importAllTest_oneNoExistingCity_WithCountryIso2Code_CountryNotExists() {
        City city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .iataCode(IATA_CODE)
                .country(Country.builder()
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .build())
                .build();

        City cityToCreate = city.toBuilder()
                .country(null)
                .build();

        cityToCreate = removeCityId(cityToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(city));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        when(countryRepository.findByIso2Code(COUNTRY_ISO_2_CODE))
                .thenReturn(Optional.empty());

        simpleImportCity.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository).create(cityToCreate);
        verify(targetRepository, never()).update(any());
        verify(countryRepository).findByIso2Code(COUNTRY_ISO_2_CODE);
    }

    @Test
    void importAllTest_oneAlreadyExistingCity_WithCountryIso2Code_CountryExists() {
        City city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .iataCode(IATA_CODE)
                .country(Country.builder()
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .build())
                .build();

        Country alreadySavedCountry = Country.builder()
                .name(COUNTRY_NAME)
                .iso2Code(COUNTRY_ISO_2_CODE)
                .build();

        City alreadySavedCity = city.toBuilder()
                .id(ANOTHER_ID)
                .build();

        City cityToUpdate = alreadySavedCity.toBuilder()
                .country(alreadySavedCountry)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(city));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(alreadySavedCity));

        when(countryRepository.findByIso2Code(COUNTRY_ISO_2_CODE))
                .thenReturn(Optional.of(alreadySavedCountry));

        simpleImportCity.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(cityToUpdate);
        verify(countryRepository).findByIso2Code(COUNTRY_ISO_2_CODE);
    }

    @Test
    void importAllTest_oneAlreadyExistingCity_WithCountryIso2Code_CountryNotExists() {
        City city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .iataCode(IATA_CODE)
                .country(Country.builder()
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .build())
                .build();

        City alreadySavedCity = city.toBuilder()
                .id(ANOTHER_ID)
                .build();

        City cityToUpdate = alreadySavedCity.toBuilder()
                .country(null)
                .build();

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(city));

        when(targetRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(alreadySavedCity));

        when(countryRepository.findByIso2Code(COUNTRY_ISO_2_CODE))
                .thenReturn(Optional.empty());

        simpleImportCity.importAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).findByIataCode(IATA_CODE);
        verify(targetRepository, never()).create(any());
        verify(targetRepository).update(cityToUpdate);
        verify(countryRepository).findByIso2Code(COUNTRY_ISO_2_CODE);
    }

    @Test
    void overrideAllTest_noCitiesToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ImportException.class, () -> simpleImportCity.overrideAll());

        verify(sourceRepository).findAll();
        verify(countryRepository, never()).findByIso2Code(anyString());
        verify(targetRepository, never()).overrideAll(anyList());
    }

    @Test
    void overrideAllTest_oneCity_WithCountryIso2Code_CountryExists() {
        City city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .iataCode(IATA_CODE)
                .country(Country.builder()
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .build())
                .build();

        Country alreadySavedCountry = Country.builder()
                .name(COUNTRY_NAME)
                .iso2Code(COUNTRY_ISO_2_CODE)
                .build();

        City cityToCreate = city.toBuilder()
                .country(alreadySavedCountry)
                .build();

        cityToCreate = removeCityId(cityToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(city));

        when(countryRepository.findByIso2Code(COUNTRY_ISO_2_CODE))
                .thenReturn(Optional.of(alreadySavedCountry));

        simpleImportCity.overrideAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).overrideAll(Lists.newArrayList(cityToCreate));
        verify(countryRepository).findByIso2Code(COUNTRY_ISO_2_CODE);
    }

    @Test
    void overrideAllTest_oneCity_WithCountryIso2Code_CountryNotExists() {
        City city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .iataCode(IATA_CODE)
                .country(Country.builder()
                        .iso2Code(COUNTRY_ISO_2_CODE)
                        .build())
                .build();

        City cityToCreate = city.toBuilder()
                .country(null)
                .build();

        cityToCreate = removeCityId(cityToCreate);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(city));

        when(countryRepository.findByIso2Code(COUNTRY_ISO_2_CODE))
                .thenReturn(Optional.empty());

        simpleImportCity.overrideAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).overrideAll(Lists.newArrayList(cityToCreate));
        verify(countryRepository).findByIso2Code(COUNTRY_ISO_2_CODE);
    }
}