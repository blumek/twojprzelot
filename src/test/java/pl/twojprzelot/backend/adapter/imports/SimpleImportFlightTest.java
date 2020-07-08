package pl.twojprzelot.backend.adapter.imports;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Flight;
import pl.twojprzelot.backend.domain.entity.FlightIdentifier;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.FlightImmutableRepository;
import pl.twojprzelot.backend.domain.port.FlightMutableRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleImportFlightTest {
    private static final int ID = 1;
    private static final String IATA_CODE = "IATA_CODE";

    private SimpleImportFlight simpleImportFlight;
    @Mock
    private FlightImmutableRepository sourceRepository;
    @Mock
    private FlightMutableRepository targetRepository;

    @BeforeEach
    void setUp() {
        simpleImportFlight = new SimpleImportFlight(sourceRepository, targetRepository);
    }

    @Test
    void overrideAllTest_noFlightsToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ImportException.class, () -> simpleImportFlight.overrideAll());

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).overrideAll(anyList());
    }

    @Test
    void overrideAllTest_oneFlightToImport() {
        Flight flight = Flight.builder()
                .id(ID)
                .flightIdentifier(FlightIdentifier.builder()
                        .iataNumber(IATA_CODE)
                        .build())
                .build();

        Flight flightToCreate = removeFlightId(flight);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(flight));

        simpleImportFlight.overrideAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).overrideAll(Lists.newArrayList(flightToCreate));
    }

    private Flight removeFlightId(Flight flight) {
        return flight.toBuilder()
                .id(0)
                .build();
    }
}