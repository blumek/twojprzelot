package pl.twojprzelot.backend.adapter.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.FlightEndpointDetails;

@ExtendWith(MockitoExtension.class)
class AssociateScheduledFlightTestHelper {
    static final int ID = 1;
    static final String AIRPORT_NAME = "AIRPORT_NAME";
    static final String AIRPORT_IATA_CODE = "AIRPORT_IATA_CODE";
    static final String ANOTHER_AIRPORT_IATA_CODE = "ANOTHER_AIRPORT_IATA_CODE";
    static final int DELAY_MINUTES = 10;
    static final String AIRLINE_ICAO_CODE = "AIRLINE_ICAO_CODE";
    static final String AIRLINE_NAME = "AIRLINE_NAME";

    Airport departureAirportShortInfo = Airport.builder()
            .iataCode(AIRPORT_IATA_CODE)
            .build();

    Airport departureAirportFullInfo = departureAirportShortInfo.toBuilder()
            .name(AIRPORT_NAME)
            .build();

    FlightEndpointDetails flightEndpointWithNullAirport = FlightEndpointDetails.builder()
            .delayMinutes(DELAY_MINUTES)
            .build();

    FlightEndpointDetails departureWithNotAssociatedAirport = FlightEndpointDetails.builder()
            .airport(departureAirportShortInfo)
            .delayMinutes(DELAY_MINUTES)
            .build();

    FlightEndpointDetails departureWithAssociatedAirport = departureWithNotAssociatedAirport.toBuilder()
            .airport(departureAirportFullInfo)
            .build();

    Airport arrivalAirportShortInfo = Airport.builder()
            .iataCode(ANOTHER_AIRPORT_IATA_CODE)
            .build();

    Airport arrivalAirportFullInfo = arrivalAirportShortInfo.toBuilder()
            .name(AIRPORT_NAME)
            .build();

    FlightEndpointDetails arrivalWithNotAssociatedAirport = FlightEndpointDetails.builder()
            .airport(arrivalAirportShortInfo)
            .delayMinutes(DELAY_MINUTES)
            .build();

    FlightEndpointDetails arrivalWithAssociatedAirport = arrivalWithNotAssociatedAirport.toBuilder()
            .airport(arrivalAirportFullInfo)
            .build();

    Airline airlineShortInfo = Airline.builder()
            .icaoCode(AIRLINE_ICAO_CODE)
            .build();

    Airline airlineFullInfo = airlineShortInfo.toBuilder()
            .name(AIRLINE_NAME)
            .build();
}