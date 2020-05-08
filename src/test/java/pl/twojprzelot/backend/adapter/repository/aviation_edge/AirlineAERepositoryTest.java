package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirlineAERepositoryTest {
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final String ANOTHER_ICAO_CODE = "ANOTHER_ICAO_CODE";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private AirlineAERepository airlineAERepository;
    @Mock
    private AviationEdgeClient aviationEdgeClient;
    @Mock
    private AirlineRequest.Builder airlineRequestBuilder;
    @Mock
    private AirlineRequest airlineRequest;

    private AirlineAE airlineAE;
    private AirlineAE anotherAirlineAE;
    private Airline expectedAirline;
    private Airline anotherExpectedAirline;

    @BeforeEach
    void setUp() {
        when(aviationEdgeClient.createAirlineRequest())
                .thenReturn(airlineRequestBuilder);

        airlineAE = new AirlineAE();
        airlineAE.setId(ID);
        airlineAE.setIcaoCode(ICAO_CODE);

        anotherAirlineAE = new AirlineAE();
        anotherAirlineAE.setId(ANOTHER_ID);
        anotherAirlineAE.setIcaoCode(ANOTHER_ICAO_CODE);

        expectedAirline = Airline.builder()
                .id(ID)
                .icaoCode(ICAO_CODE)
                .build();

        anotherExpectedAirline = Airline.builder()
                .id(ANOTHER_ID)
                .icaoCode(ANOTHER_ICAO_CODE)
                .build();
    }

    @Test
    void findByIataCodeTest_airlineWithGivenIataCodeNotExists() {
        when(airlineRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(airlineRequestBuilder);

        when(airlineRequestBuilder.create())
                .thenReturn(airlineRequest);

        when(airlineRequest.get())
                .thenReturn(Lists.newArrayList());

        assertEquals(Optional.empty(), airlineAERepository.findByIataCode(IATA_CODE));

        verify(airlineRequestBuilder).iataCode(IATA_CODE);
        verify(airlineRequest).get();
    }

    @Test
    void findByIataCodeTest_airlineWithGivenIataCodeExists() {
        when(airlineRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(airlineRequestBuilder);

        when(airlineRequestBuilder.create())
                .thenReturn(airlineRequest);

        when(airlineRequest.get())
                .thenReturn(Lists.newArrayList(airlineAE, anotherAirlineAE));

        assertEquals(Optional.of(expectedAirline), airlineAERepository.findByIataCode(IATA_CODE));

        verify(airlineRequestBuilder).iataCode(IATA_CODE);
        verify(airlineRequest).get();
    }

    @Test
    void findByIcaoCodeTest_airlineWithGivenIcaoCodeNotExists() {
        when(airlineRequestBuilder.create())
                .thenReturn(airlineRequest);

        when(airlineRequest.get())
                .thenReturn(Lists.newArrayList());

        assertEquals(Optional.empty(), airlineAERepository.findByIcaoCode(ICAO_CODE));

        verify(airlineRequest).get();
    }

    @Test
    void findByIcaoCodeTest_airlineWithGivenIcaoCodeExists() {
        when(airlineRequestBuilder.create())
                .thenReturn(airlineRequest);

        when(airlineRequest.get())
                .thenReturn(Lists.newArrayList(anotherAirlineAE, airlineAE));

        assertEquals(Optional.of(expectedAirline), airlineAERepository.findByIcaoCode(ICAO_CODE));

        verify(airlineRequest).get();
    }

    @Test
    void findAllTest_noAirlinesAvailable() {
        when(airlineRequestBuilder.create())
                .thenReturn(airlineRequest);

        when(airlineRequest.get())
                .thenReturn(Lists.newArrayList());

        List<Airline> foundAirlines = airlineAERepository.findAll();
        assertTrue(foundAirlines.isEmpty());

        verify(airlineRequest).get();
    }

    @Test
    void findAllTest_twoAirlinesAvailable() {
        when(airlineRequestBuilder.create())
                .thenReturn(airlineRequest);

        when(airlineRequest.get())
                .thenReturn(Lists.newArrayList(airlineAE, anotherAirlineAE));

        List<Airline> foundAirlines = airlineAERepository.findAll();
        assertThat(foundAirlines, containsInAnyOrder(expectedAirline, anotherExpectedAirline));

        verify(airlineRequest).get();
    }
}