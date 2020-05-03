package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduledFlightEntityTest extends ScheduledFlightEntityTestHelper {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void fromTest_fullObjects() {
        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutFlightIdentifier() {
        scheduledFlight = scheduledFlight.toBuilder()
                .flightIdentifier(null)
                .build();

        scheduledFlightEntity.setFlightIdentifier(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutDeparture() {
        scheduledFlight = scheduledFlight.toBuilder()
                .departure(null)
                .build();

        scheduledFlightEntity.setDeparture(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutArrival() {
        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(null)
                .build();

        scheduledFlightEntity.setArrival(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void fromTest_withoutAirline() {
        scheduledFlight = scheduledFlight.toBuilder()
                .airline(null)
                .build();

        scheduledFlightEntity.setAirline(null);

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_fullObjects() {
        assertEquals(scheduledFlight, scheduledFlightEntity.toScheduledFlight());
    }

    @Test
    void toScheduledFlightTest_withoutFlightIdentifier() {
        scheduledFlightEntity.setFlightIdentifier(null);

        scheduledFlight = scheduledFlight.toBuilder()
                .flightIdentifier(null)
                .build();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_withoutDeparture() {
        scheduledFlightEntity.setDeparture(null);

        scheduledFlight = scheduledFlight.toBuilder()
                .departure(null)
                .build();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_withoutArrival() {
        scheduledFlightEntity.setArrival(null);

        scheduledFlight = scheduledFlight.toBuilder()
                .arrival(null)
                .build();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }

    @Test
    void toScheduledFlightTest_withoutAirline() {
        scheduledFlightEntity.setAirline(null);

        scheduledFlight = scheduledFlight.toBuilder()
                .airline(null)
                .build();

        assertEquals(scheduledFlightEntity, ScheduledFlightEntity.from(scheduledFlight));
    }
}