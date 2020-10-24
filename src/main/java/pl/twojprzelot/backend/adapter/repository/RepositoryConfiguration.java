package pl.twojprzelot.backend.adapter.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirportImmutableRepository;

@Configuration
class RepositoryConfiguration {

    @Bean
    AssociateScheduledFlight associateScheduledFlight(@Qualifier("airportDatabaseRepository") AirportImmutableRepository airportRepository,
                                                      @Qualifier("airlineDatabaseRepository") AirlineImmutableRepository airlineRepository) {
        return new AssociateScheduledFlight(airportRepository, airlineRepository);
    }
}
