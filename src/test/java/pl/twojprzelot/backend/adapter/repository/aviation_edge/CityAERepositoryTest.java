package pl.twojprzelot.backend.adapter.repository.aviation_edge;

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
class CityAERepositoryTest {
    private static final String IATA_CODE = "IATA_CODE";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private CityAERepository cityAERepository;
    @Mock
    private AviationEdgeClient aviationEdgeClient;
    @Mock
    private CityRequest.Builder cityRequestBuilder;
    @Mock
    private CityRequest cityRequest;

    private CityAE cityAE;
    private CityAE anotherCityAE;
    private City expectedCity;
    private City anotherExpectedCity;

    @BeforeEach
    void setUp() {
        cityAE = new CityAE();
        cityAE.setId(ID);

        anotherCityAE = new CityAE();
        anotherCityAE.setId(ANOTHER_ID);

        expectedCity = City.builder()
                .id(ID)
                .build();

        anotherExpectedCity = City.builder()
                .id(ANOTHER_ID)
                .build();
    }

    @Test
    void findAllTest_noCitiesAvailable() {
        when(aviationEdgeClient.createCityRequest())
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.create())
                .thenReturn(cityRequest);

        when(cityRequest.get())
                .thenReturn(Lists.newArrayList());

        List<City> foundCities = cityAERepository.findAll();
        assertTrue(foundCities.isEmpty());

        verify(cityRequest).get();
    }

    @Test
    void findAllTest_twoCitiesAvailable() {
        when(aviationEdgeClient.createCityRequest())
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.create())
                .thenReturn(cityRequest);

        when(cityRequest.get())
                .thenReturn(Lists.newArrayList(cityAE, anotherCityAE));

        List<City> foundCities = cityAERepository.findAll();
        assertThat(foundCities, containsInAnyOrder(expectedCity, anotherExpectedCity));

        verify(cityRequest).get();
    }

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeNotExists() {
        when(aviationEdgeClient.createCityRequest())
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.create())
                .thenReturn(cityRequest);

        when(cityRequest.get())
                .thenReturn(Lists.newArrayList());

        assertEquals(Optional.empty(), cityAERepository.findByIataCode(IATA_CODE));

        verify(cityRequestBuilder).iataCode(IATA_CODE);
        verify(cityRequest).get();
    }

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeExists() {
        when(aviationEdgeClient.createCityRequest())
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.create())
                .thenReturn(cityRequest);

        when(cityRequest.get())
                .thenReturn(Lists.newArrayList(cityAE, anotherCityAE));

        assertEquals(Optional.of(expectedCity), cityAERepository.findByIataCode(IATA_CODE));

        verify(cityRequestBuilder).iataCode(IATA_CODE);
        verify(cityRequest).get();
    }

    @Test
    void findByIataCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> cityAERepository.findByIataCode(null));

        verify(aviationEdgeClient, never()).createCityRequest();
        verify(cityRequest, never()).get();
    }
}