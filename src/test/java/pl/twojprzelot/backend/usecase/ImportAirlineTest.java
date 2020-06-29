package pl.twojprzelot.backend.usecase;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airline;
import pl.twojprzelot.backend.domain.exception.ImportException;
import pl.twojprzelot.backend.domain.port.AirlineImmutableRepository;
import pl.twojprzelot.backend.domain.port.AirlineMutableRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportAirlineTest {
    private static final int ID = 1;
    private static final String NAME = "NAME";
    private static final String IATA_CODE = "IATA_CODE";

    private ImportAirline importAirline;
    @Mock
    private AirlineImmutableRepository sourceRepository;
    @Mock
    private AirlineMutableRepository targetRepository;

    @BeforeEach
    void setUp() {
        importAirline = new ImportAirline(sourceRepository, targetRepository);
    }

    @Test
    void overrideAllTest_noAirlinesToImport() {
        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList());

        assertThrows(ImportException.class, () -> importAirline.overrideAll());

        verify(sourceRepository).findAll();
        verify(targetRepository, never()).overrideAll(anyList());
    }

    @Test
    void overrideAllTest_oneAirlineToImport() {
        Airline airline = Airline.builder()
                .id(ID)
                .name(NAME)
                .iataCode(IATA_CODE)
                .build();

        Airline airlineToCreate = removeAirlineId(airline);

        when(sourceRepository.findAll())
                .thenReturn(Lists.newArrayList(airline));

        importAirline.overrideAll();

        verify(sourceRepository).findAll();
        verify(targetRepository).overrideAll(Lists.newArrayList(airlineToCreate));
    }

    private Airline removeAirlineId(Airline airline) {
        return airline.toBuilder()
                .id(0)
                .build();
    }
}