package pl.twojprzelot.backend.adapter.repository.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;
import pl.twojprzelot.backend.adapter.repository.AssociateScheduledFlight;

@Configuration
class DatabaseRepositoryConfig {
    @Bean
    ScheduledFlightImmutableRepository scheduledFlightWithAssociationDatabaseRepository(@Qualifier("scheduledFlightAERepository") ScheduledFlightImmutableRepository scheduledFlightRepository,
                                                                                        AssociateScheduledFlight associateScheduledFlight) {
        return new AssociatedScheduledFlightDatabaseRepository(scheduledFlightRepository, associateScheduledFlight);
    }
}
