package pl.twojprzelot.backend.adapter.repository.database;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.port.FlightMutableRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Component
@RequiredArgsConstructor
class FlightDatabaseRepository implements FlightMutableRepository {
    private final FlightSpringRepository repository;

    @Override
    public List<Flight> findAll() {
        return repository.findAll()
                .stream()
                .map(FlightEntity::toFlight)
                .collect(toList());
    }

    @Override
    public List<Flight> findAllByIataNumber(String iataNumber) {
        return repository.findAllByFlightIdentifierIataNumber(iataNumber)
                .stream()
                .map(FlightEntity::toFlight)
                .collect(toList());
    }

    @Override
    public List<Flight> findAllByIcaoNumber(String icaoNumber) {
        return repository.findAllByFlightIdentifierIcaoNumber(icaoNumber)
                .stream()
                .map(FlightEntity::toFlight)
                .collect(toList());
    }

    @Override
    public Flight create(@NonNull Flight flight) {
        FlightEntity flightToCreate = FlightEntity.from(flight);
        FlightEntity createdFlight = repository.save(flightToCreate);
        return createdFlight.toFlight();
    }

    @Transactional
    @Override
    public List<Flight> overrideAll(@NonNull Iterable<Flight> flights) {
        removeAllAndFlush();

        List<FlightEntity> flightsToCreate = stream(flights.spliterator(), false)
                .map(FlightEntity::from)
                .collect(toList());

        List<FlightEntity> createdFlights = repository.saveAll(flightsToCreate);

        return createdFlights.stream()
                .map(FlightEntity::toFlight)
                .collect(toList());
    }

    private void removeAllAndFlush() {
        repository.deleteAll();
        repository.flush();
    }

    @Override
    public Flight update(@NonNull Flight flight) {
        if (!hasIdentifier(flight))
            throw new IllegalArgumentException("Identifier not valid");

        FlightEntity flightToUpdate = FlightEntity.from(flight);
        FlightEntity updatedFlight = repository.save(flightToUpdate);
        return updatedFlight.toFlight();
    }

    private boolean hasIdentifier(Flight flight) {
        return flight.getId() > 0;
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
