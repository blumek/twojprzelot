package pl.twojprzelot.backend.adapter.repository;


import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.entity.Airport;
import pl.twojprzelot.backend.domain.entity.FlightEndpointDetails;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirportImmutableRepository;

import java.util.Optional;

@RequiredArgsConstructor
public final class AssociateScheduledFlight {
    private final AirportImmutableRepository airportRepository;
    private final AirlineImmutableRepository airlineRepository;


    public ScheduledFlight getAssociatedScheduledFlight(ScheduledFlight scheduledFlight) {
        ScheduledFlight.ScheduledFlightBuilder scheduledFlightBuilder = scheduledFlight.toBuilder();

        FlightEndpointDetails departure = scheduledFlight.getDeparture();
        if (departure != null) {
            FlightEndpointDetails departureWithAssociation = getFlightEndpointDetailsWithAssociation(departure);
            scheduledFlightBuilder.departure(departureWithAssociation);
        }

        FlightEndpointDetails arrival = scheduledFlight.getArrival();
        if (arrival != null) {
            FlightEndpointDetails arrivalWithAssociation = getFlightEndpointDetailsWithAssociation(arrival);
            scheduledFlightBuilder.arrival(arrivalWithAssociation);
        }

        Airline airline = getAssociatedAirline(scheduledFlight.getAirline());
        scheduledFlightBuilder.airline(airline);

        return scheduledFlightBuilder.build();
    }

    private FlightEndpointDetails getFlightEndpointDetailsWithAssociation(FlightEndpointDetails flightEndpointDetails) {
        Airport airport = flightEndpointDetails.getAirport();
        if (hasIataCode(airport)) {
            Optional<Airport> alreadySaveAirport = findAlreadySavedAirport(airport);
            if (alreadySaveAirport.isPresent())
                return flightEndpointDetailsWithAirport(flightEndpointDetails, alreadySaveAirport.get());
        }

        return flightEndpointDetailsWithoutAirport(flightEndpointDetails);
    }

    private boolean hasIataCode(Airport airport) {
        return airport != null && airport.getIataCode() != null;
    }

    private Optional<Airport> findAlreadySavedAirport(Airport airport) {
        return airportRepository.findByIataCode(airport.getIataCode());
    }

    private FlightEndpointDetails flightEndpointDetailsWithAirport(FlightEndpointDetails flightEndpointDetails,
                                                                   Airport airport) {
        return flightEndpointDetails.toBuilder()
                .airport(airport)
                .build();
    }

    private FlightEndpointDetails flightEndpointDetailsWithoutAirport(FlightEndpointDetails flightEndpointDetails) {
        return flightEndpointDetails.toBuilder()
                .airport(null)
                .build();
    }

    private Airline getAssociatedAirline(Airline airline) {
        if (hasIcaoCode(airline)) {
            Optional<Airline> alreadySavedAirline = airlineRepository.findByIcaoCode(airline.getIcaoCode());
            if (alreadySavedAirline.isPresent())
                return alreadySavedAirline.get();
        }

        return null;
    }

    private boolean hasIcaoCode(Airline airline) {
        return airline != null && airline.getIcaoCode() != null;
    }
}
