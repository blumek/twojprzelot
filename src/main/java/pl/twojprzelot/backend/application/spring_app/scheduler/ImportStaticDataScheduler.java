package pl.twojprzelot.backend.application.spring_app.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.usecase.ImportStaticData;

@Slf4j
@Component
@RequiredArgsConstructor
final class ImportStaticDataScheduler {
    private final ImportStaticData importStaticData;

    @Scheduled(fixedRate = Long.MAX_VALUE)
    void importStaticData() {
        log.info("Import static data - scheduled task");
        importStaticData.overrideAll();
    }
}
