package pl.twojprzelot.backend.application.spring_app.scheduler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.twojprzelot.backend.domain.port.ImportFlight;
import pl.twojprzelot.backend.domain.port.ImportStaticData;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulerConfiguration {

    @Bean
    ImportFlightsScheduler importFlightsScheduler(ImportFlight importFlight) {
        return new ImportFlightsScheduler(importFlight);
    }

    @Bean
    ImportStaticDataScheduler importStaticDataScheduler(ImportStaticData importStaticData) {
        return new ImportStaticDataScheduler(importStaticData);
    }
}
