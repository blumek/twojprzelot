package pl.twojprzelot.backend.adapter.imports;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.FlightMutableRepository;
import pl.twojprzelot.backend.domain.port.ImportFlight;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
final class SimpleImportFlight implements ImportFlight {
    private final FlightImmutableRepository sourceRepository;
    private final FlightMutableRepository targetRepository;

    @Override
    public void overrideAll() {
        log.info("Overriding all flights");

        List<Flight> importedFlights = sourceRepository.findAll();
        if (importedFlights.isEmpty())
            throw new ImportException("No flights to import");

        List<Flight> flightsToCreate = importedFlights.stream()
                .map(this::getFlightWithoutId)
                .collect(toList());

        targetRepository.overrideAll(flightsToCreate);

        log.info("Overridden all flights");
    }

    private Flight getFlightWithoutId(Flight flight) {
        return flight.toBuilder()
                .id(0)
                .build();
    }
}
