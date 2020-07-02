package pl.twojprzelot.backend.application.spring_app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.adapter.repository.AssociateScheduledFlight;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirportImmutableRepository;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;
import pl.twojprzelot.backend.usecase.FindFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

@Configuration
class SpringConfig {

    @Bean
    FindScheduledFlight findScheduledFlight(@Qualifier("scheduledFlightWithAssociationDatabaseRepository")
                                                    ScheduledFlightImmutableRepository repository) {
        return new FindScheduledFlight(repository);
    }

    @Bean
    FindFlight findFlight(@Qualifier("flightDatabaseRepository") FlightImmutableRepository repository) {
        return new FindFlight(repository);
    }

    @Bean
    AssociateScheduledFlight associateScheduledFlight(@Qualifier("airportDatabaseRepository") AirportImmutableRepository airportRepository,
                                                      @Qualifier("airlineDatabaseRepository") AirlineImmutableRepository airlineRepository) {
        return new AssociateScheduledFlight(airportRepository, airlineRepository);
    }
}
