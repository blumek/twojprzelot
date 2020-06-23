package pl.twojprzelot.backend.adapter.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.twojprzelot.backend.domain.entity.*;

@Mapper
public interface DomainMapper {
    @AfterMapping
    default void removeScheduledFlightEmptyObjects(@MappingTarget ScheduledFlight.ScheduledFlightBuilder
                                                           scheduledFlightBuilder) {
        ScheduledFlight scheduledFlight = scheduledFlightBuilder.build();

        FlightIdentifier emptyFlightIdentifier = FlightIdentifier.builder().build();
        if (scheduledFlight.getFlightIdentifier() == null ||
                scheduledFlight.getFlightIdentifier().equals(emptyFlightIdentifier))
            scheduledFlightBuilder.flightIdentifier(null);


        FlightEndpointDetails emptyFlightEndpointDetails = FlightEndpointDetails.builder().build();
        if (scheduledFlight.getDeparture() == null || scheduledFlight.getDeparture().equals(emptyFlightEndpointDetails)) {
            scheduledFlightBuilder.departure(null);
        }

        if (scheduledFlight.getArrival() == null || scheduledFlight.getArrival().equals(emptyFlightEndpointDetails)) {
            scheduledFlightBuilder.arrival(null);
        }

        Airline emptyAirline = Airline.builder().build();
        if (scheduledFlight.getAirline() == null || scheduledFlight.getAirline().equals(emptyAirline))
            scheduledFlightBuilder.airline(null);
    }

    @AfterMapping
    default void removeFlightEndpointDetailsEmptyObjects(@MappingTarget FlightEndpointDetails.FlightEndpointDetailsBuilder
                                                                     flightEndpointDetailsBuilder) {
        FlightEndpointDetails flightEndpointDetails = flightEndpointDetailsBuilder.build();

        Airport emptyAirport = Airport.builder().build();
        if (flightEndpointDetails.getAirport() == null || flightEndpointDetails.getAirport().equals(emptyAirport))
            flightEndpointDetailsBuilder.airport(null);

        FlightAirportDetails emptyFlightAirportDetails = FlightAirportDetails.builder().build();
        if (flightEndpointDetails.getFlightAirportDetails() == null ||
                flightEndpointDetails.getFlightAirportDetails().equals(emptyFlightAirportDetails))
            flightEndpointDetailsBuilder.flightAirportDetails(null);
    }

    @AfterMapping
    default void removeFlightEmptyObjects(@MappingTarget Flight.FlightBuilder flightBuilder) {
        Flight flight = flightBuilder.build();

        FlightIdentifier emptyFlightIdentifier = FlightIdentifier.builder().build();
        if (flight.getFlightIdentifier() == null || flight.getFlightIdentifier().equals(emptyFlightIdentifier))
            flightBuilder.flightIdentifier(null);

        GeographicPosition emptyGeographicPosition = GeographicPosition.builder().build();
        if (flight.getGeographicPosition() == null || flight.getGeographicPosition().equals(emptyGeographicPosition))
            flightBuilder.geographicPosition(null);

        AirplaneSpeed emptyAirplaneSpeed = AirplaneSpeed.builder().build();
        if (flight.getAirplaneSpeed() == null || flight.getAirplaneSpeed().equals(emptyAirplaneSpeed))
            flightBuilder.airplaneSpeed(null);
    }

    @AfterMapping
    default void removeAirportEmptyObjects(@MappingTarget Airport.AirportBuilder airportBuilder) {
        Airport airport = airportBuilder.build();

        GeographicLocation emptyGeographicLocation = GeographicLocation.builder().build();
        if (airport.getGeographicLocation() == null || airport.getGeographicLocation().equals(emptyGeographicLocation))
            airportBuilder.geographicLocation(null);

        City emptyCity = City.builder().build();
        if (airport.getCity() == null || airport.getCity().equals(emptyCity))
            airportBuilder.city(null);
    }

    @AfterMapping
    default void removeCityEmptyObjects(@MappingTarget City.CityBuilder cityBuilder) {
        City city = cityBuilder.build();

        GeographicLocation emptyGeographicLocation = GeographicLocation.builder().build();
        if (city.getGeographicLocation() == null || city.getGeographicLocation().equals(emptyGeographicLocation))
            cityBuilder.geographicLocation(null);

        Country emptyCountry = Country.builder().build();
        if (city.getCountry() == null || city.getCountry().equals(emptyCountry))
            cityBuilder.country(null);
    }

    @AfterMapping
    default void removeCountryEmptyObjects(@MappingTarget Country.CountryBuilder countryBuilder) {
        Country country = countryBuilder.build();

        Currency emptyCurrency = Currency.builder().build();
        if (country.getCurrency() == null || country.getCurrency().equals(emptyCurrency))
            countryBuilder.currency(null);
    }
}
