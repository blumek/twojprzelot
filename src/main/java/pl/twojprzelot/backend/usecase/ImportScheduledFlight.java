package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.twojprzelot.backend.adapter.repository.AssociateScheduledFlight;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightMutableRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public final class ImportScheduledFlight {
    private final ScheduledFlightImmutableRepository sourceRepository;
    private final ScheduledFlightMutableRepository targetRepository;
    private final AssociateScheduledFlight associateScheduledFlight;

    public void overrideAll() {
        log.info("Overriding all scheduled flights");

        List<ScheduledFlight> importedScheduledFlights = sourceRepository.findAll();
        if (importedScheduledFlights.isEmpty())
            throw new ImportException("No Scheduled Flights to import");

        List<ScheduledFlight> scheduledFlightsToCreate = importedScheduledFlights.stream()
                .map(this::getScheduledFlightToCreate)
                .collect(toList());

        targetRepository.overrideAll(scheduledFlightsToCreate);

        log.info("Overridden all scheduled flights");
    }

    private ScheduledFlight getScheduledFlightToCreate(ScheduledFlight scheduledFlight) {
        ScheduledFlight scheduledFlightToCreate = associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight);
        scheduledFlightToCreate = getScheduledFlightWithoutId(scheduledFlightToCreate);
        return scheduledFlightToCreate;
    }

    private ScheduledFlight getScheduledFlightWithoutId(ScheduledFlight scheduledFlight) {
        return scheduledFlight.toBuilder()
                .id(0)
                .build();
    }
}
