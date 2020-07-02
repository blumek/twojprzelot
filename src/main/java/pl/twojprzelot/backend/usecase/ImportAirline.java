package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirlineMutableRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public final class ImportAirline {
    private final AirlineImmutableRepository sourceRepository;
    private final AirlineMutableRepository targetRepository;

    public void overrideAll() {
        log.info("Overriding all airlines");

        List<Airline> importedAirlines = sourceRepository.findAll();
        if (importedAirlines.isEmpty())
            throw new ImportException("No airlines to import");

        List<Airline> airlinesToCreate = importedAirlines.stream()
                .map(this::getAirlineWithoutId)
                .collect(toList());

        targetRepository.overrideAll(airlinesToCreate);

        log.info("Overridden all airlines");
    }

    private Airline getAirlineWithoutId(Airline airline) {
        return airline.toBuilder()
                .id(0)
                .build();
    }
}
