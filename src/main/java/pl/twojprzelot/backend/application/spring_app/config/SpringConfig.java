package pl.twojprzelot.backend.application.spring_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.adapter.controller.TimetableController;
import pl.twojprzelot.backend.usecase.FindScheduledFlight;

@Configuration
class SpringConfig {
    @Bean
    TimetableController timetableController() {
        return new TimetableController(findScheduledFlight());
    }

    @Bean
    FindScheduledFlight findScheduledFlight() {
        return new FindScheduledFlight(null);
    }
}
