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

    @InjectMocks
    private CityDatabaseRepository cityDatabaseRepository;
    @Mock
    private CitySpringRepository citySpringRepository;

    private CityEntity cityEntity;
    private CityEntity anotherCityEntity;
    private City expectedCity;
    private City anotherExpectedCity;

    @BeforeEach
    void setUp() {
        cityEntity = new CityEntity();
        cityEntity.setId(ID);
        cityEntity.setIataCode(IATA_CODE);

        anotherCityEntity = new CityEntity();
        anotherCityEntity.setId(ANOTHER_ID);
        anotherCityEntity.setIataCode(IATA_CODE);

        expectedCity = City.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .build();

        anotherExpectedCity = City.builder()
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
        assertThat(foundCities, containsInAnyOrder(expectedCity, anotherExpectedCity));

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

        assertEquals(Optional.of(expectedCity), cityDatabaseRepository.findByIataCode(IATA_CODE));

        verify(citySpringRepository).findByIataCode(IATA_CODE);
    }

    @Test
    void createTest() {
        when(citySpringRepository.save(cityEntity))
                .thenReturn(anotherCityEntity);

        City city = cityDatabaseRepository.create(expectedCity);
        assertEquals(anotherExpectedCity, city);

        verify(citySpringRepository).save(cityEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> cityDatabaseRepository.create(null));

        verify(citySpringRepository, never()).save(null);
    }
}