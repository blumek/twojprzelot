package pl.twojprzelot.backend.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;

@Configuration
class UseCaseConfiguration {
    @Bean
    FindScheduledFlight findScheduledFlight(@Qualifier("scheduledFlightWithAssociationDatabaseRepository")
                                                    ScheduledFlightImmutableRepository repository) {
        return new FindScheduledFlight(repository);
    }

    @Bean
    FindFlight findFlight(@Qualifier("flightDatabaseRepository") FlightImmutableRepository repository) {
        return new FindFlight(repository);
    }
}
