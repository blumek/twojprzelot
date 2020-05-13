package pl.twojprzelot.backend.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightAirportDetailsTest {
    private static final String GATE = "GATE";
    private static final String ANOTHER_GATE = "ANOTHER_GATE";
    private static final String TERMINAL = "TERMINAL";

    private FlightAirportDetails firstFlightAirportDetails;
    private FlightAirportDetails sameFlightAirportDetailsAsFirstFlightAirportDetails;
    private FlightAirportDetails anotherFlightAirportDetails;

    @BeforeEach
    void setUp() {
        firstFlightAirportDetails = FlightAirportDetails.builder()
                .gate(GATE)
                .terminal(TERMINAL)
                .build();

        sameFlightAirportDetailsAsFirstFlightAirportDetails = FlightAirportDetails.builder()
                .gate(GATE)
                .terminal(TERMINAL)
                .build();

        anotherFlightAirportDetails = FlightAirportDetails.builder()
                .gate(ANOTHER_GATE)
                .terminal(TERMINAL)
                .build();
    }

    @Test
    void builderTest_gate() {
        FlightAirportDetails flightAirportDetails = FlightAirportDetails.builder()
                .gate(GATE)
                .build();

        assertEquals(GATE, flightAirportDetails.getGate());
    }

    @Test
    void builderTest_terminal() {
        FlightAirportDetails flightAirportDetails = FlightAirportDetails.builder()
                .terminal(TERMINAL)
                .build();

        assertEquals(TERMINAL, flightAirportDetails.getTerminal());
    }

    @Test
    void builderTest_toBuilder() {
        FlightAirportDetails flightAirportDetails = FlightAirportDetails.builder()
                .gate(GATE)
                .terminal(TERMINAL)
                .build();

        FlightAirportDetails modifiedFlightAirportDetails = flightAirportDetails.toBuilder()
                .gate(ANOTHER_GATE)
                .build();

        FlightAirportDetails expectedFlightAirportDetails = FlightAirportDetails.builder()
                .gate(ANOTHER_GATE)
                .terminal(TERMINAL)
                .build();

        assertEquals(expectedFlightAirportDetails, modifiedFlightAirportDetails);
    }

    @Test
    void equalsContractTest() {
        EqualsVerifier.forClass(FlightAirportDetails.class).verify();
    }
}