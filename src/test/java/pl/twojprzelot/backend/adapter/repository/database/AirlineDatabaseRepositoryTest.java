package pl.twojprzelot.backend.adapter.repository.database;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Airline;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineDatabaseRepositoryTest {
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;
    private static final String IATA_CODE = "IATA_CODE";
    private static final String ICAO_CODE = "ICAO_CODE";
    private static final String NAME = "NAME";

    @InjectMocks
    private AirlineDatabaseRepository airlineDatabaseRepository;
    @Mock
    private AirlineSpringRepository airlineSpringRepository;

    private AirlineEntity airlineEntity;
    private AirlineEntity anotherAirlineEntity;
    private Airline airline;
    private Airline anotherAirline;

    @BeforeEach
    void setUp() {
        airlineEntity = new AirlineEntity();
        airlineEntity.setId(ID);
        airlineEntity.setIataCode(IATA_CODE);
        airlineEntity.setIcaoCode(ICAO_CODE);

        anotherAirlineEntity = new AirlineEntity();
        anotherAirlineEntity.setId(ANOTHER_ID);
        anotherAirlineEntity.setIataCode(IATA_CODE);
        anotherAirlineEntity.setIcaoCode(ICAO_CODE);

        airline = Airline.builder()
                .id(ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();

        anotherAirline = Airline.builder()
                .id(ANOTHER_ID)
                .iataCode(IATA_CODE)
                .icaoCode(ICAO_CODE)
                .build();
    }

    @Test
    void findAllTest_noAirlineAvailable() {
        when(airlineSpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<Airline> foundAirline = airlineDatabaseRepository.findAll();
        assertTrue(foundAirline.isEmpty());

        verify(airlineSpringRepository).findAll();
    }

    @Test
    void findAllTest_twoAirlineAvailable() {
        when(airlineSpringRepository.findAll())
                .thenReturn(Lists.newArrayList(airlineEntity, anotherAirlineEntity));

        List<Airline> foundAirline = airlineDatabaseRepository.findAll();
        assertThat(foundAirline, containsInAnyOrder(airline, anotherAirline));

        verify(airlineSpringRepository).findAll();
    }

    @Test
    void findAllByIataCodeTest_airlineWithGivenIataCodeNotExists() {
        when(airlineSpringRepository.findAllByIataCode(IATA_CODE))
                .thenReturn(Lists.newArrayList());

        List<Airline> foundAirlines = airlineDatabaseRepository.findAllByIataCode(IATA_CODE);
        assertTrue(foundAirlines.isEmpty());

        verify(airlineSpringRepository).findAllByIataCode(IATA_CODE);
    }

    @Test
    void findAllByIataCodeTest_airlineWithGivenIataCodeExists() {
        when(airlineSpringRepository.findAllByIataCode(IATA_CODE))
                .thenReturn(Lists.newArrayList(airlineEntity));

        List<Airline> foundAirlines = airlineDatabaseRepository.findAllByIataCode(IATA_CODE);
        assertThat(foundAirlines, containsInAnyOrder(airline));

        verify(airlineSpringRepository).findAllByIataCode(IATA_CODE);
    }

    @Test
    void findAllByIataCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airlineDatabaseRepository.findAllByIataCode(null));

        verify(airlineSpringRepository, never()).findAllByIataCode(null);
    }

    @Test
    void findByIcaoCodeTest_airlineWithGivenIcaoCodeNotExists() {
        when(airlineSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.empty());

        assertEquals(Optional.empty(), airlineDatabaseRepository.findByIcaoCode(ICAO_CODE));

        verify(airlineSpringRepository).findByIcaoCode(ICAO_CODE);
    }

    @Test
    void findByIcaoCodeTest_airlineWithGivenIcaoCodeExists() {
        when(airlineSpringRepository.findByIcaoCode(ICAO_CODE))
                .thenReturn(Optional.of(airlineEntity));

        assertEquals(Optional.of(airline), airlineDatabaseRepository.findByIcaoCode(ICAO_CODE));

        verify(airlineSpringRepository).findByIcaoCode(ICAO_CODE);
    }

    @Test
    void findByIcaoCodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airlineDatabaseRepository.findByIcaoCode(null));

        verify(airlineSpringRepository, never()).findByIcaoCode(null);
    }

    @Test
    void createTest() {
        when(airlineSpringRepository.save(airlineEntity))
                .thenReturn(anotherAirlineEntity);

        Airline createdAirline = airlineDatabaseRepository.create(airline);
        assertEquals(anotherAirline, createdAirline);

        verify(airlineSpringRepository).save(airlineEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airlineDatabaseRepository.create(null));

        verify(airlineSpringRepository, never()).save(null);
    }

    @Test
    void overrideAllTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airlineDatabaseRepository.overrideAll(null));

        verify(airlineSpringRepository, never()).deleteAll();
        verify(airlineSpringRepository, never()).flush();
        verify(airlineSpringRepository, never()).saveAll(any());
    }

    @Test
    void overrideAllTest_noAirlinesToImport() {
        List<Airline> createdAirlines = airlineDatabaseRepository.overrideAll(Lists.newArrayList());
        assertTrue(createdAirlines.isEmpty());

        verify(airlineSpringRepository).deleteAll();
        verify(airlineSpringRepository).flush();
        verify(airlineSpringRepository).saveAll(Lists.newArrayList());
    }

    @Test
    void overrideAllTest_oneAirlineToImport() {
        Airline airline = Airline.builder()
                .name(NAME)
                .build();

        Airline createdAirline = airline.toBuilder()
                .id(ID)
                .build();

        AirlineEntity airlineEntity = new AirlineEntity();
        airlineEntity.setName(NAME);

        AirlineEntity createdAirlineEntity = new AirlineEntity();
        createdAirlineEntity.setId(ID);
        createdAirlineEntity.setName(NAME);

        List<AirlineEntity> airlineEntitiesToCreate = Lists.newArrayList(airlineEntity);
        List<AirlineEntity> createdAirlineEntities = Lists.newArrayList(createdAirlineEntity);
        when(airlineSpringRepository.saveAll(airlineEntitiesToCreate))
                .thenReturn(createdAirlineEntities);

        List<Airline> airlinesToCreate = Lists.newArrayList(airline);
        List<Airline> createdAirlines = airlineDatabaseRepository.overrideAll(airlinesToCreate);
        assertThat(createdAirlines, containsInAnyOrder(createdAirline));

        verify(airlineSpringRepository).deleteAll();
        verify(airlineSpringRepository).flush();
        verify(airlineSpringRepository).saveAll(airlineEntitiesToCreate);
    }

    @Test
    void updateTest_entityWithId() {
        when(airlineSpringRepository.save(airlineEntity))
                .thenReturn(anotherAirlineEntity);

        Airline updatedAirline = airlineDatabaseRepository.update(airline);
        assertEquals(anotherAirline, updatedAirline);

        verify(airlineSpringRepository).save(airlineEntity);
    }

    @Test
    void updateTest_entityWithoutId() {
        removeId();
        assertThrows(IllegalArgumentException.class, () -> airlineDatabaseRepository.update(airline));

        verify(airlineSpringRepository, never()).save(airlineEntity);
    }

    private void removeId() {
        airline = airline.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void updateTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> airlineDatabaseRepository.update(null));

        verify(airlineSpringRepository, never()).save(null);
    }

    @Test
    void removeAllTest() {
        airlineDatabaseRepository.removeAll();

        verify(airlineSpringRepository).deleteAll();
    }
}