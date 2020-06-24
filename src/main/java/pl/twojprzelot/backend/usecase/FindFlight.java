package pl.twojprzelot.backend.usecase;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;

import java.util.List;

@RequiredArgsConstructor
public final class FindFlight {
    private final FlightImmutableRepository repository;

    public List<Flight> findAll() {
        return repository.findAll();
    }
}
