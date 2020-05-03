package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.City;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityDatabaseRepositoryTest {
    private static final int CITY_ID = 1;
    private static final String CITY_IATA_CODE = "IATA_CODE";

    @InjectMocks
    private CityDatabaseRepository cityDatabaseRepository;
    @Mock
    private CitySpringRepository citySpringRepository;

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeNotExists() {
        when(citySpringRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), cityDatabaseRepository.findByIataCode(CITY_IATA_CODE));
    }

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeExists() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(CITY_ID);
        cityEntity.setIataCode(CITY_IATA_CODE);

        when(citySpringRepository.findByIataCode(CITY_IATA_CODE))
                .thenReturn(Optional.of(cityEntity));

        City expectedCity = City.builder()
                .id(CITY_ID)
                .iataCode(CITY_IATA_CODE)
                .build();

        assertEquals(Optional.of(expectedCity), cityDatabaseRepository.findByIataCode(CITY_IATA_CODE));
    }
}