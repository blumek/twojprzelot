package pl.twojprzelot.backend.application.spring_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.adapter.controller.TimetableController;
import pl.twojprzelot.backend.adapter.repository.database.ScheduledFlightDatabaseRepository;
import pl.twojprzelot.backend.adapter.repository.database.ScheduledFlightSpringRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightRepository;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

@Configuration
class SpringConfig {
    @Bean
    TimetableController timetableController(FindScheduledFlight findScheduledFlight) {
        return new TimetableController(findScheduledFlight);
    }

    @Bean
    FindScheduledFlight findScheduledFlight(ScheduledFlightRepository scheduledFlightRepository) {
        return new FindScheduledFlight(scheduledFlightRepository);
    }

    @Bean
    ScheduledFlightRepository springScheduledFlightRepository(ScheduledFlightSpringRepository
                                                                              scheduledFlightSpringRepository) {
        return new ScheduledFlightDatabaseRepository(scheduledFlightSpringRepository);
    }
}
