package pl.twojprzelot.backend.application.spring_app.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.usecase.ImportFlight;

@Slf4j
@Component
@RequiredArgsConstructor
final class ImportFlightsScheduler {
    private final ImportFlight importFlight;

    @Scheduled(fixedDelayString = "${scheduling.flights.delayString}")
    void importFlights() {
        log.info("Import flights - scheduled task");
        importFlight.overrideAll();
    }
}
