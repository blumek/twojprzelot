package pl.twojprzelot.backend.adapter.imports;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirlineMutableRepository;
import pl.twojprzelot.backend.domain.port.ImportAirline;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
final class SimpleImportAirline implements ImportAirline {
    private final AirlineImmutableRepository sourceRepository;
    private final AirlineMutableRepository targetRepository;

    @Override
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
