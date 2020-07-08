package pl.twojprzelot.backend.adapter.imports;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.adapter.repository.AssociateScheduledFlight;
import pl.twojprzelot.backend.domain.entity.FlightEndpointDetails;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;
import pl.twojprzelot.backend.domain.entity.ScheduledFlight;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.ScheduledFlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.ScheduledFlightMutableRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleImportScheduledFlightTest {
    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";

    private SimpleImportScheduledFlight simpleImportScheduledFlight;
    @Mock
    private ScheduledFlightImmutableRepository sourceRepository;
    @Mock
    private ScheduledFlightMutableRepository targetRepository;
    @Mock
    private AssociateScheduledFlight associateScheduledFlight;

    @BeforeEach
    void setUp() {
        simpleImportScheduledFlight = new SimpleImportScheduledFlight(sourceRepository, targetRepository, associateScheduledFlight);
    }

    @Test
    void overrideAllTest_noScheduledFlightsToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ImportException.class, () -> simpleImportScheduledFlight.overrideAll());

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).overrideAll(anyList());
    }

    @Test
    void overrideAllTest_oneScheduledFlightToImport() {
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                .id(ID)
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_CODE)
                        .build())
                .build();

        ScheduledFlight scheduledFlightWithAssociation = scheduledFlight.toBuilder()
                .departure(FlightEndpointDetails.builder()
                        .delayMinutes(10)
                        .build())
                .build();

        ScheduledFlight scheduledFlightToCreate = removeScheduledFlightId(scheduledFlightWithAssociation);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(scheduledFlight));

        when(associateScheduledFlight.getAssociatedScheduledFlight(scheduledFlight))
                .thenReturn(scheduledFlightWithAssociation);

        simpleImportScheduledFlight.overrideAll();

        verify(sourceRepository).findAll();
        verify(associateScheduledFlight).getAssociatedScheduledFlight(scheduledFlight);
        verify(targetRepository).overrideAll(Lists.newArrayList(scheduledFlightToCreate));
    }

    private ScheduledFlight removeScheduledFlightId(ScheduledFlight scheduledFlight) {
        return scheduledFlight.toBuilder()
                .id(0)
                .build();
    }
}