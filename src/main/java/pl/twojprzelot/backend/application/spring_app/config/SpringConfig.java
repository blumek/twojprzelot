package pl.twojprzelot.backend.application.spring_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;
import pl.twojprzelot.backend.usecase.FindFlight;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

@Configuration
class SpringConfig {
    @Bean
    FindScheduledFlight findScheduledFlight(ScheduledFlightImmutableRepository repository) {
        return new FindScheduledFlight(repository);
    }

    @Bean
    FindFlight findFlight(FlightImmutableRepository repository) {
        return new FindFlight(repository);
    }
}
