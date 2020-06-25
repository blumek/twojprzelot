package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightAERepositoryTest {
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";
    private static final String FIRST_NUMBER = "10";
    private static final String SECOND_NUMBER = "20";

    @InjectMocks
    private FlightAERepository FlightAERepository;
    @Mock
    private AviationEdgeClient aviationEdgeClient;
    @Mock
    private FlightRequestBuilder FlightRequestBuilder;
    @Mock
    private AviationEdgeRequest<FlightAE> FlightRequest;

    private FlightAE FlightAE;
    private FlightAE anotherFlightAE;
    private Flight expectedFlight;
    private Flight anotherExpectedFlight;

    @BeforeEach
    void setUp() {
        FlightIdentifierAE firstFlightIdentifierAE = new FlightIdentifierAE();
        firstFlightIdentifierAE.setIataNumber(IATA_NUMBER);
        firstFlightIdentifierAE.setIcaoNumber(ICAO_NUMBER);
        firstFlightIdentifierAE.setNumber(FIRST_NUMBER);

        FlightIdentifierAE secondFlightIdentifierAE = new FlightIdentifierAE();
        secondFlightIdentifierAE.setIataNumber(IATA_NUMBER);
        secondFlightIdentifierAE.setIcaoNumber(ICAO_NUMBER);
        secondFlightIdentifierAE.setNumber(SECOND_NUMBER);

        FlightAE = new FlightAE();
        FlightAE.setFlightIdentifier(firstFlightIdentifierAE);

        anotherFlightAE = new FlightAE();
        anotherFlightAE.setFlightIdentifier(secondFlightIdentifierAE);

        expectedFlight = Flight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .icaoNumber(ICAO_NUMBER)
                        .number(FIRST_NUMBER)
                        .build())
                .build();

        anotherExpectedFlight = Flight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .icaoNumber(ICAO_NUMBER)
                        .number(SECOND_NUMBER)
                        .build())
                .build();
    }

    @Test
    void findAllTest_noFlightsAvailable() {
        when(aviationEdgeClient.createFlightRequest())
                .thenReturn(FlightRequestBuilder);

        when(FlightRequestBuilder.build())
                .thenReturn(FlightRequest);

        when(FlightRequest.get())
                .thenReturn(Lists.newArrayList());

        List<Flight> foundFlights = FlightAERepository.findAll();
        assertTrue(foundFlights.isEmpty());

        verify(FlightRequest).get();
    }

    @Test
    void findAllTest_twoFlightsAvailable() {
        when(aviationEdgeClient.createFlightRequest())
                .thenReturn(FlightRequestBuilder);

        when(FlightRequestBuilder.build())
                .thenReturn(FlightRequest);

        when(FlightRequest.get())
                .thenReturn(Lists.newArrayList(FlightAE, anotherFlightAE));

        List<Flight> foundFlights = FlightAERepository.findAll();
        assertThat(foundFlights, containsInAnyOrder(expectedFlight, anotherExpectedFlight));

        verify(FlightRequest).get();
    }
}