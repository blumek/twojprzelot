package pl.twojprzelot.backend.application.spring_app.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.usecase.ImportFlight;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImportFlightsScheduler {
    private final ImportFlight importFlight;

    @Scheduled(fixedRateString = "${scheduling.flights.delayString}")
    void importFlights() {
        log.info("Importing flights");
        importFlight.overrideAll();
        log.info("Flights import done");
    }
}
