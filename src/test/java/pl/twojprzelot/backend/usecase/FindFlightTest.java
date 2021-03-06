package pl.twojprzelot.backend.usecase;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindFlightTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";

    @InjectMocks
    private FindFlight findFlight;
    @Mock
    private FlightImmutableRepository flightImmutableRepository;

    private Flight firstFlight;
    private Flight secondFlight;

    @BeforeEach
    void setUp() {
        firstFlight = Flight.builder()
                .id(ID)
                .build();

        secondFlight = Flight.builder()
                .id(ANOTHER_ID)
                .build();
    }

    @Test
    void findAll_noAvailableFlights() {
        when(flightImmutableRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<Flight> foundFlights = findFlight.findAll();
        assertTrue(foundFlights.isEmpty());

        verify(flightImmutableRepository).findAll();
    }

    @Test
    void findAll_twoAvailableFlights() {
        when(flightImmutableRepository.findAll())
                .thenReturn(Lists.newArrayList(firstFlight, secondFlight));

        List<Flight> foundFlights = findFlight.findAll();
        assertThat(foundFlights, containsInAnyOrder(firstFlight, secondFlight));

        verify(flightImmutableRepository).findAll();
    }

    @Test
    void findCurrentByFlightIdentifier_noCurrentFlightWithGivenFlightIdentifier() {
        when(flightImmutableRepository.findAllByIataNumber(any()))
                .thenReturn(Lists.newArrayList());

        when(flightImmutableRepository.findAllByIcaoNumber(any()))
                .thenReturn(Lists.newArrayList());

        Optional<Flight> foundFlight = findFlight.findCurrentByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(Optional.empty(), foundFlight);
    }

    @Test
    void findCurrentByFlightIdentifier_flightWithGivenFlightIdentifier_IataAvailable() {
        when(flightImmutableRepository.findAllByIataNumber(any()))
                .thenReturn(Lists.newArrayList(firstFlight, secondFlight));

        Optional<Flight> expectedFlight = Optional.of(firstFlight);

        Optional<Flight> foundFlight = findFlight.findCurrentByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(expectedFlight, foundFlight);
    }

    @Test
    void findCurrentByFlightIdentifier_flightWithGivenFlightIdentifier_IcaoAvailable() {
        when(flightImmutableRepository.findAllByIataNumber(any()))
                .thenReturn(Lists.newArrayList());

        when(flightImmutableRepository.findAllByIcaoNumber(any()))
                .thenReturn(Lists.newArrayList(firstFlight, secondFlight));

        Optional<Flight> expectedFlight = Optional.of(firstFlight);

        Optional<Flight> foundFlight = findFlight.findCurrentByFlightIdentifier(FLIGHT_IDENTIFIER);

        assertEquals(expectedFlight, foundFlight);
    }
}