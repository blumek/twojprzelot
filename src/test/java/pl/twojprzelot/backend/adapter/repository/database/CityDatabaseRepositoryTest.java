package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.City;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String NAME = "NAME";

    @InjectMocks
    private CityDatabaseRepository cityDatabaseRepository;
    @Mock
    private CitySpringRepository citySpringRepository;

    private CityEntity cityEntity;
    private CityEntity anotherCityEntity;
    private City city;
    private City anotherCity;

    @BeforeEach
    void setUp() {
        cityEntity = new CityEntity();
        cityEntity.setId(ID);
        cityEntity.setIataCode(IATA_CODE);

        anotherCityEntity = new CityEntity();
        anotherCityEntity.setId(ANOTHER_ID);
        anotherCityEntity.setIataCode(IATA_CODE);

        city = City.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .build();

        anotherCity = City.builder()
                .id(ANOTHER_ID)
                .iataCode(IATA_CODE)
                .build();
    }

    @Test
    void findAllTest_noCitiesAvailable() {
        when(citySpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<City> foundCities = cityDatabaseRepository.findAll();
        assertTrue(foundCities.isEmpty());

        verify(citySpringRepository).findAll();
    }

    @Test
    void findAllTest_twoCitiesAvailable() {
        when(citySpringRepository.findAll())
                .thenReturn(Lists.newArrayList(cityEntity, anotherCityEntity));

        List<City> foundCities = cityDatabaseRepository.findAll();
        assertThat(foundCities, containsInAnyOrder(city, anotherCity));

        verify(citySpringRepository).findAll();
    }

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeNotExists() {
        when(citySpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), cityDatabaseRepository.findByIataCode(IATA_CODE));

        verify(citySpringRepository).findByIataCode(IATA_CODE);
    }

    @Test
    void findByIataCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> cityDatabaseRepository.findByIataCode(null));

        verify(citySpringRepository, never()).findByIataCode(null);
    }

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeExists() {
        when(citySpringRepository.findByIataCode(IATA_CODE))
                .thenReturn(Optional.of(cityEntity));

        assertEquals(Optional.of(city), cityDatabaseRepository.findByIataCode(IATA_CODE));

        verify(citySpringRepository).findByIataCode(IATA_CODE);
    }

    @Test
    void createTest() {
        when(citySpringRepository.save(cityEntity))
                .thenReturn(anotherCityEntity);

        City createdCity = cityDatabaseRepository.create(city);
        assertEquals(anotherCity, createdCity);

        verify(citySpringRepository).save(cityEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> cityDatabaseRepository.create(null));

        verify(citySpringRepository, never()).save(null);
    }

    @Test
    void overrideAllTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> cityDatabaseRepository.overrideAll(null));

        verify(citySpringRepository, never()).deleteAll();
        verify(citySpringRepository, never()).flush();
        verify(citySpringRepository, never()).saveAll(any());
    }

    @Test
    void overrideAllTest_noCitiesToImport() {
        List<City> createdCities = cityDatabaseRepository.overrideAll(Lists.newArrayList());
        assertTrue(createdCities.isEmpty());

        verify(citySpringRepository).deleteAll();
        verify(citySpringRepository).flush();
        verify(citySpringRepository).saveAll(Lists.newArrayList());
    }

    @Test
    void overrideAllTest_oneCityToImport() {
        City city = City.builder()
                .name(NAME)
                .build();

        City createdCity = city.toBuilder()
                .id(ID)
                .build();

        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(NAME);

        CityEntity createdCityEntity = new CityEntity();
        createdCityEntity.setId(ID);
        createdCityEntity.setName(NAME);

        List<CityEntity> cityEntitiesToCreate = Lists.newArrayList(cityEntity);
        List<CityEntity> createdCityEntities = Lists.newArrayList(createdCityEntity);
        when(citySpringRepository.saveAll(cityEntitiesToCreate))
                .thenReturn(createdCityEntities);

        List<City> citiesToCreate = Lists.newArrayList(city);
        List<City> createdCities = cityDatabaseRepository.overrideAll(citiesToCreate);
        assertThat(createdCities, containsInAnyOrder(createdCity));

        verify(citySpringRepository).deleteAll();
        verify(citySpringRepository).flush();
        verify(citySpringRepository).saveAll(cityEntitiesToCreate);
    }

    @Test
    void updateTest_entityWithId() {
        when(citySpringRepository.save(cityEntity))
                .thenReturn(anotherCityEntity);

        City updatedCity = cityDatabaseRepository.update(city);
        assertEquals(anotherCity, updatedCity);

        verify(citySpringRepository).save(cityEntity);
    }

    @Test
    void updateTest_entityWithoutId() {
        removeId();

        assertThrows(IllegalArgumentException.class, () -> cityDatabaseRepository.update(city));

        verify(citySpringRepository, never()).save(cityEntity);
    }

    private void removeId() {
        city = city.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void updateTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> cityDatabaseRepository.update(null));

        verify(citySpringRepository, never()).save(null);
    }

    @Test
    void removeAllTest() {
        cityDatabaseRepository.removeAll();

        verify(citySpringRepository).deleteAll();
    }
}