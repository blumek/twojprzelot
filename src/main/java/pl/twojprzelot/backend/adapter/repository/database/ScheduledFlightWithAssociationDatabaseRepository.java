package pl.twojprzelot.backend.adapter.repository.database;


import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;
import pl.twojprzelot.backend.adapter.repository.AssociateScheduledFlight;

import java.util.List;

import static java.util.stream.Collectors.toList;

final class ScheduledFlightWithAssociationDatabaseRepository extends ForwardingScheduledFlightDatabaseRepository
        implements ScheduledFlightImmutableRepository {
    private final AssociateScheduledFlight associateScheduledFlight;

    public ScheduledFlightWithAssociationDatabaseRepository(ScheduledFlightImmutableRepository repository,
                                                            AssociateScheduledFlight associateScheduledFlight) {
        super(repository);
        this.associateScheduledFlight = associateScheduledFlight;
    }

    @Override
    public List<ScheduledFlight> findAll() {
        List<ScheduledFlight> foundScheduledFlights = super.findAll();
        return foundScheduledFlights.stream()
                .map(associateScheduledFlight::getAssociatedScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIataNumber(String iataNumber) {
        List<ScheduledFlight> foundScheduledFlights = super.findAllByIataNumber(iataNumber);
        return foundScheduledFlights.stream()
                .map(associateScheduledFlight::getAssociatedScheduledFlight)
                .collect(toList());
    }

    @Override
    public List<ScheduledFlight> findAllByIcaoNumber(String icaoNumber) {
        List<ScheduledFlight> foundScheduledFlights = super.findAllByIcaoNumber(icaoNumber);
        return foundScheduledFlights.stream()
                .map(associateScheduledFlight::getAssociatedScheduledFlight)
                .collect(toList());
    }
}
