package pl.twojprzelot.backend.application.spring_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.adapter.repository.database.ScheduledFlightDatabaseRepository;
import pl.twojprzelot.backend.adapter.repository.database.ScheduledFlightSpringRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightMutableRepository;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

@Configuration
class SpringConfig {
    @Bean
    FindScheduledFlight findScheduledFlight(ScheduledFlightImmutableRepository repository) {
        return new FindScheduledFlight(repository);
    }

    @Bean
    ScheduledFlightMutableRepository springScheduledFlightRepository(ScheduledFlightSpringRepository
                                                                              scheduledFlightSpringRepository) {
        return new ScheduledFlightDatabaseRepository(scheduledFlightSpringRepository);
    }
}
