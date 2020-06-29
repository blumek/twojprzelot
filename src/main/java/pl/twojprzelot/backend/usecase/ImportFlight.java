package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.FlightMutableRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public final class ImportFlight {
    private final FlightImmutableRepository sourceRepository;
    private final FlightMutableRepository targetRepository;

    public void overrideAll() {
        List<Flight> importedFlights = sourceRepository.findAll();
        if (importedFlights.isEmpty())
            throw new ImportException("No flights to import");

        List<Flight> flightsToCreate = importedFlights.stream()
                .map(this::getFlightWithoutId)
                .collect(toList());

        targetRepository.overrideAll(flightsToCreate);
    }

    private Flight getFlightWithoutId(Flight flight) {
        return flight.toBuilder()
                .id(0)
                .build();
    }
}
