package pl.twojprzelot.backend.adapter.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirportImmutableRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssociateScheduledFlightTest extends AssociateScheduledFlightTestHelper {

    @InjectMocks
    private AssociateScheduledFlight associateScheduledFlight;
    @Mock
    private AirportImmutableRepository airportRepository;
    @Mock
    private AirlineImmutableRepository airlineRepository;

    @Test
    void getAssociatedScheduledFlightTest_withoutAllAssociations() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .build();

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlight, associatedScheduledFlight);

        verify(airportRepository, never()).findByIataCode(anyString());
        verify(airlineRepository, never()).findByIcaoCode(anyString());
    }

    @Test
    void getAssociatedScheduledFlightTest_withDeparture_airportNotExist() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .departure(departureWithNotAssociatedAirport)
                .build();

        ScheduledFlight scheduledFlightWithoutDepartureAirport = scheduledFlight.toBuilder()
                .departure(flightEndpointWithNullAirport)
                .build();

        when(airportRepository.findByIataCode(AIRPORT_IATA_CODE))
                .thenReturn(Optional.empty());

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlightWithoutDepartureAirport, associatedScheduledFlight);

        verify(airportRepository).findByIataCode(AIRPORT_IATA_CODE);
        verify(airlineRepository, never()).findByIcaoCode(anyString());
    }

    @Test
    void getAssociatedScheduledFlightTest_withDeparture_airportExists() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .departure(departureWithNotAssociatedAirport)
                .build();

        ScheduledFlight scheduledFlightWithDeparture = scheduledFlight.toBuilder()
                .departure(departureWithAssociatedAirport)
                .build();

        when(airportRepository.findByIataCode(AIRPORT_IATA_CODE))
                .thenReturn(Optional.of(departureAirportFullInfo));

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlightWithDeparture, associatedScheduledFlight);

        verify(airportRepository).findByIataCode(AIRPORT_IATA_CODE);
        verify(airlineRepository, never()).findByIcaoCode(anyString());
    }

    @Test
    void getAssociatedScheduledFlightTest_withArrival_airportNotExist() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .arrival(arrivalWithNotAssociatedAirport)
                .build();

        ScheduledFlight scheduledFlightWithoutArrivalAirport = scheduledFlight.toBuilder()
                .arrival(flightEndpointWithNullAirport)
                .build();

        when(airportRepository.findByIataCode(ANOTHER_AIRPORT_IATA_CODE))
                .thenReturn(Optional.empty());

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlightWithoutArrivalAirport, associatedScheduledFlight);

        verify(airportRepository).findByIataCode(ANOTHER_AIRPORT_IATA_CODE);
        verify(airlineRepository, never()).findByIcaoCode(anyString());
    }

    @Test
    void getAssociatedScheduledFlightTest_withArrival_airportExists() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .arrival(arrivalWithNotAssociatedAirport)
                .build();

        ScheduledFlight scheduledFlightWithDeparture = scheduledFlight.toBuilder()
                .arrival(arrivalWithAssociatedAirport)
                .build();

        when(airportRepository.findByIataCode(ANOTHER_AIRPORT_IATA_CODE))
                .thenReturn(Optional.of(arrivalAirportFullInfo));

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlightWithDeparture, associatedScheduledFlight);

        verify(airportRepository).findByIataCode(ANOTHER_AIRPORT_IATA_CODE);
        verify(airlineRepository, never()).findByIcaoCode(anyString());
    }

    @Test
    void getAssociatedScheduledFlightTest_withAirline_notExistingAirline() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .airline(airlineShortInfo)
                .build();

        ScheduledFlight scheduledFlightWithAirline = scheduledFlight.toBuilder()
                .airline(null)
                .build();

        when(airlineRepository.findByIcaoCode(AIRLINE_ICAO_CODE))
                .thenReturn(Optional.empty());

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlightWithAirline, associatedScheduledFlight);

        verify(airportRepository, never()).findByIataCode(anyString());
        verify(airlineRepository).findByIcaoCode(AIRLINE_ICAO_CODE);
    }

    @Test
    void getAssociatedScheduledFlightTest_withAirline_existingAirline() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .airline(airlineShortInfo)
                .build();

        ScheduledFlight scheduledFlightWithAirline = scheduledFlight.toBuilder()
                .airline(airlineFullInfo)
                .build();

        when(airlineRepository.findByIcaoCode(AIRLINE_ICAO_CODE))
                .thenReturn(Optional.of(airlineFullInfo));

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlightWithAirline, associatedScheduledFlight);

        verify(airportRepository, never()).findByIataCode(anyString());
        verify(airlineRepository).findByIcaoCode(AIRLINE_ICAO_CODE);
    }

    @Test
    void getAssociatedScheduledFlightTest_withAllAssociations() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .departure(departureWithNotAssociatedAirport)
                .arrival(arrivalWithNotAssociatedAirport)
                .airline(airlineShortInfo)
                .build();

        ScheduledFlight scheduledFlightWithAssociations = scheduledFlight.toBuilder()
                .departure(departureWithAssociatedAirport)
                .arrival(arrivalWithAssociatedAirport)
                .airline(airlineFullInfo)
                .build();

        when(airportRepository.findByIataCode(AIRPORT_IATA_CODE))
                .thenReturn(Optional.of(departureAirportFullInfo));

        when(airportRepository.findByIataCode(ANOTHER_AIRPORT_IATA_CODE))
                .thenReturn(Optional.of(arrivalAirportFullInfo));

        when(airlineRepository.findByIcaoCode(AIRLINE_ICAO_CODE))
                .thenReturn(Optional.of(airlineFullInfo));

        ScheduledFlight associatedScheduledFlight =
                associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);

        assertEquals(scheduledFlightWithAssociations, associatedScheduledFlight);

        verify(airportRepository).findByIataCode(AIRPORT_IATA_CODE);
        verify(airportRepository).findByIataCode(ANOTHER_AIRPORT_IATA_CODE);
        verify(airlineRepository).findByIcaoCode(AIRLINE_ICAO_CODE);
    }
}