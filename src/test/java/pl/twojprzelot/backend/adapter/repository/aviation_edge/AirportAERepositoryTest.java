package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airport;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportAERepositoryTest {
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final String ANOTHER_ICAO_CODE = "ANOTHER_ICAO_CODE";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private AirportAERepository airportAERepository;
    @Mock
    private AviationEdgeClient aviationEdgeClient;
    @Mock
    private AirportRequest.Builder airportRequestBuilder;
    @Mock
    private AirportRequest airportRequest;

    private AirportAE airportAE;
    private AirportAE anotherAirportAE;
    private Airport expectedAirport;
    private Airport anotherExpectedAirport;

    @BeforeEach
    void setUp() {
        airportAE = new AirportAE();
        airportAE.setId(ID);
        airportAE.setIcaoCode(ICAO_CODE);

        anotherAirportAE = new AirportAE();
        anotherAirportAE.setId(ANOTHER_ID);
        anotherAirportAE.setIcaoCode(ANOTHER_ICAO_CODE);

        expectedAirport = Airport.builder()
                .id(ID)
                .icaoCode(ICAO_CODE)
                .build();

        anotherExpectedAirport = Airport.builder()
                .id(ANOTHER_ID)
                .icaoCode(ANOTHER_ICAO_CODE)
                .build();
    }

    @Test
    void findAllTest_noAirportsAvailable() {
        when(aviationEdgeClient.createAirportRequest())
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.create())
                .thenReturn(airportRequest);

        when(airportRequest.get())
                .thenReturn(Lists.newArrayList());

        List<Airport> foundAirports = airportAERepository.findAll();
        assertTrue(foundAirports.isEmpty());

        verify(airportRequest).get();
    }

    @Test
    void findAllTest_twoAirportsAvailable() {
        when(aviationEdgeClient.createAirportRequest())
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.create())
                .thenReturn(airportRequest);

        when(airportRequest.get())
                .thenReturn(Lists.newArrayList(airportAE, anotherAirportAE));

        List<Airport> foundAirports = airportAERepository.findAll();
        assertThat(foundAirports, containsInAnyOrder(expectedAirport, anotherExpectedAirport));

        verify(airportRequest).get();
    }

    @Test
    void findByIataCodeTest_airportWithGivenIataCodeNotExists() {
        when(aviationEdgeClient.createAirportRequest())
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.create())
                .thenReturn(airportRequest);

        when(airportRequest.get())
                .thenReturn(Lists.newArrayList());

        assertEquals(Optional.empty(), airportAERepository.findByIataCode(IATA_CODE));

        verify(airportRequestBuilder).iataCode(IATA_CODE);
        verify(airportRequest).get();
    }

    @Test
    void findByIataCodeTest_airportWithGivenIataCodeExists() {
        when(aviationEdgeClient.createAirportRequest())
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.iataCode(IATA_CODE))
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.create())
                .thenReturn(airportRequest);

        when(airportRequest.get())
                .thenReturn(Lists.newArrayList(airportAE, anotherAirportAE));

        assertEquals(Optional.of(expectedAirport), airportAERepository.findByIataCode(IATA_CODE));

        verify(airportRequestBuilder).iataCode(IATA_CODE);
        verify(airportRequest).get();
    }

    @Test
    void findByIataCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airportAERepository.findByIataCode(null));

        verify(aviationEdgeClient, never()).createAirportRequest();
        verify(airportRequest, never()).get();
    }

    @Test
    void findByIcaoCodeTest_airportWithGivenIcaoCodeNotExists() {
        when(aviationEdgeClient.createAirportRequest())
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.create())
                .thenReturn(airportRequest);

        when(airportRequest.get())
                .thenReturn(Lists.newArrayList());

        assertEquals(Optional.empty(), airportAERepository.findByIcaoCode(ICAO_CODE));

        verify(airportRequest).get();
    }

    @Test
    void findByIcaoCodeTest_airportWithGivenIcaoCodeExists() {
        when(aviationEdgeClient.createAirportRequest())
                .thenReturn(airportRequestBuilder);

        when(airportRequestBuilder.create())
                .thenReturn(airportRequest);

        when(airportRequest.get())
                .thenReturn(Lists.newArrayList(anotherAirportAE, airportAE));

        assertEquals(Optional.of(expectedAirport), airportAERepository.findByIcaoCode(ICAO_CODE));

        verify(airportRequest).get();
    }

    @Test
    void findByIcaoCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airportAERepository.findByIcaoCode(null));

        verify(aviationEdgeClient, never()).createAirportRequest();
        verify(airportRequest, never()).get();
    }
}