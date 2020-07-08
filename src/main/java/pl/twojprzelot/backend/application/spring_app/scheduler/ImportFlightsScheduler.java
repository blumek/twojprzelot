package pl.twojprzelot.backend.application.spring_app.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import pl.twojprzelot.backend.domain.port.ImportFlight;

@Slf4j
@RequiredArgsConstructor
final class ImportFlightsScheduler {
    private final ImportFlight importFlight;

    @Scheduled(fixedDelayString = "${scheduling.flights.delayString}")
    void importFlights() {
        log.info("Import flights - scheduled task");
        importFlight.overrideAll();
    }
}
