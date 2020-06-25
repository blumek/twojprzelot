package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;

import java.util.List;

import static java.lang.Long.MAX_VALUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static pl.twojprzelot.backend.adapter.repository.aviation_edge.ScheduledFlightRequestBuilder.Type.DEPARTURE;

@ExtendWith(MockitoExtension.class)
class ScheduledFlightAERepositoryTest {
    private static final String IATA_NUMBER = "IATA_NUMBER";
    private static final String ICAO_NUMBER = "ICAO_NUMBER";
    private static final String FIRST_NUMBER = "10";
    private static final String SECOND_NUMBER = "20";

    @InjectMocks
    private ScheduledFlightAERepository scheduledFlightAERepository;
    @Mock
    private AviationEdgeClient aviationEdgeClient;
    @Mock
    private ScheduledFlightRequestBuilder scheduledFlightRequestBuilder;
    @Mock
    private AviationEdgeRequest<ScheduledFlightAE> scheduledFlightRequest;

    private ScheduledFlightAE scheduledFlightAE;
    private ScheduledFlightAE anotherScheduledFlightAE;
    private ScheduledFlight expectedScheduledFlight;
    private ScheduledFlight anotherExpectedScheduledFlight;

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

        scheduledFlightAE = new ScheduledFlightAE();
        scheduledFlightAE.setFlightIdentifier(firstFlightIdentifierAE);

        anotherScheduledFlightAE = new ScheduledFlightAE();
        anotherScheduledFlightAE.setFlightIdentifier(secondFlightIdentifierAE);

        expectedScheduledFlight = ScheduledFlight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .icaoNumber(ICAO_NUMBER)
                        .number(FIRST_NUMBER)
                        .build())
                .build();

        anotherExpectedScheduledFlight = ScheduledFlight.builder()
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_NUMBER)
                        .icaoNumber(ICAO_NUMBER)
                        .number(SECOND_NUMBER)
                        .build())
                .build();
    }

    @Test
    void findAllTest_noScheduledFlightsAvailable() {
        when(aviationEdgeClient.createScheduledFlightRequest())
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.limit(MAX_VALUE))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.type(DEPARTURE))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.build())
                .thenReturn(scheduledFlightRequest);

        when(scheduledFlightRequest.get())
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundScheduledFlights = scheduledFlightAERepository.findAll();
        assertTrue(foundScheduledFlights.isEmpty());

        verify(scheduledFlightRequestBuilder).limit(MAX_VALUE);
        verify(scheduledFlightRequestBuilder).type(DEPARTURE);
        verify(scheduledFlightRequest).get();
    }

    @Test
    void findAllTest_twoScheduledFlightsAvailable() {
        when(aviationEdgeClient.createScheduledFlightRequest())
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.limit(MAX_VALUE))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.type(DEPARTURE))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.build())
                .thenReturn(scheduledFlightRequest);

        when(scheduledFlightRequest.get())
                .thenReturn(Lists.newArrayList(scheduledFlightAE, anotherScheduledFlightAE));

        List<ScheduledFlight> foundScheduledFlights = scheduledFlightAERepository.findAll();
        assertThat(foundScheduledFlights, containsInAnyOrder(expectedScheduledFlight, anotherExpectedScheduledFlight));

        verify(scheduledFlightRequestBuilder).limit(MAX_VALUE);
        verify(scheduledFlightRequestBuilder).type(DEPARTURE);
        verify(scheduledFlightRequest).get();
    }

    @Test
    void findAllByIataNumberTest_scheduledFlightWithGivenIataNumberNotExists() {
        when(aviationEdgeClient.createScheduledFlightRequest())
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.iataNumber(IATA_NUMBER))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.build())
                .thenReturn(scheduledFlightRequest);

        when(scheduledFlightRequest.get())
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundScheduledFlights = scheduledFlightAERepository.findAllByIataNumber(IATA_NUMBER);
        assertTrue(foundScheduledFlights.isEmpty());

        verify(scheduledFlightRequestBuilder).iataNumber(IATA_NUMBER);
        verify(scheduledFlightRequest).get();
    }

    @Test
    void findAllByIataNumberTest_scheduledFlightWithGivenIataNumberExists() {
        when(aviationEdgeClient.createScheduledFlightRequest())
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.iataNumber(IATA_NUMBER))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.build())
                .thenReturn(scheduledFlightRequest);

        when(scheduledFlightRequest.get())
                .thenReturn(Lists.newArrayList(scheduledFlightAE, anotherScheduledFlightAE));

        List<ScheduledFlight> foundScheduledFlights = scheduledFlightAERepository.findAllByIataNumber(IATA_NUMBER);
        assertThat(foundScheduledFlights, containsInAnyOrder(expectedScheduledFlight, anotherExpectedScheduledFlight));

        verify(scheduledFlightRequestBuilder).iataNumber(IATA_NUMBER);
        verify(scheduledFlightRequest).get();
    }

    @Test
    void findAllByIataNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightAERepository.findAllByIataNumber(null));

        verify(aviationEdgeClient, never()).createScheduledFlightRequest();
        verify(scheduledFlightRequest, never()).get();
    }

    @Test
    void findAllByIcaoNumberTest_scheduledFlightWithGivenIcaoNumberNotExists() {
        when(aviationEdgeClient.createScheduledFlightRequest())
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.icaoNumber(ICAO_NUMBER))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.build())
                .thenReturn(scheduledFlightRequest);

        when(scheduledFlightRequest.get())
                .thenReturn(Lists.newArrayList());

        List<ScheduledFlight> foundScheduledFlights = scheduledFlightAERepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertTrue(foundScheduledFlights.isEmpty());

        verify(scheduledFlightRequestBuilder).icaoNumber(ICAO_NUMBER);
        verify(scheduledFlightRequest).get();
    }

    @Test
    void findAllByIcaoNumberTest_scheduledFlightWithGivenIcaoNumberExists() {
        when(aviationEdgeClient.createScheduledFlightRequest())
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.icaoNumber(ICAO_NUMBER))
                .thenReturn(scheduledFlightRequestBuilder);

        when(scheduledFlightRequestBuilder.build())
                .thenReturn(scheduledFlightRequest);

        when(scheduledFlightRequest.get())
                .thenReturn(Lists.newArrayList(scheduledFlightAE, anotherScheduledFlightAE));

        List<ScheduledFlight> foundScheduledFlights = scheduledFlightAERepository.findAllByIcaoNumber(ICAO_NUMBER);
        assertThat(foundScheduledFlights, containsInAnyOrder(expectedScheduledFlight, anotherExpectedScheduledFlight));

        verify(scheduledFlightRequestBuilder).icaoNumber(ICAO_NUMBER);
        verify(scheduledFlightRequest).get();
    }

    @Test
    void findAllByIcaoNumberTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> scheduledFlightAERepository.findAllByIcaoNumber(null));

        verify(aviationEdgeClient, never()).createScheduledFlightRequest();
        verify(scheduledFlightRequest, never()).get();
    }
}