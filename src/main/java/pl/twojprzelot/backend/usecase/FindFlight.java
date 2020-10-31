package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public final class FindFlight {
    private final FlightImmutableRepository repository;

    public List<Flight> findAll() {
        return repository.findAll();
    }

    public List<Flight> findAllByFlightIdentifier(String flightIdentifier) {
        List<Flight> flightsByIataNumber = repository.findAllByIataNumber(flightIdentifier);
        if (!flightsByIataNumber.isEmpty())
            return flightsByIataNumber;

        return repository.findAllByIcaoNumber(flightIdentifier);
    }

    public Optional<Flight> findCurrentByFlightIdentifier(String flightIdentifier) {
        return findAllByFlightIdentifier(flightIdentifier)
                .stream()
                .findFirst();
    }
}
