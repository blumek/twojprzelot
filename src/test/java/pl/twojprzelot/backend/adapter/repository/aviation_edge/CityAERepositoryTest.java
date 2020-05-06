package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.City;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @BeforeEach
    void setUp() {
        when(aviationEdgeClient.createCityRequest())
                .thenReturn(cityRequestBuilder);

        cityAE = new CityAE();
        cityAE.setId(ID);

        anotherCityAE = new CityAE();
        anotherCityAE.setId(ANOTHER_ID);

        expectedCity = City.builder()
                .id(ID)
                .build();
    }

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeNotExists() {
        when(cityRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.create())
                .thenReturn(cityRequest);

        when(cityRequest.get())
                .thenReturn(Lists.newArrayList());

        assertEquals(Optional.empty(), cityAERepository.findByIataCode(IATA_CODE));

        verify(cityRequest).get();
    }

    @Test
    void findByIataCodeTest_cityWithGivenIataCodeExists() {
        when(cityRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(cityRequestBuilder);

        when(cityRequestBuilder.create())
                .thenReturn(cityRequest);

        when(cityRequest.get())
                .thenReturn(Lists.newArrayList(cityAE, anotherCityAE));

        assertEquals(Optional.of(expectedCity), cityAERepository.findByIataCode(IATA_CODE));

        verify(cityRequest).get();
    }
}