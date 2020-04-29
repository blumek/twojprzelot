package pl.twojprzelot.backend.application.spring_app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.adapter.controller.TimetableController;
import pl.twojprzelot.backend.adapter.controller.model.ScheduledFlightWeb;

import java.util.Optional;

import static io.restassured.http.ContentType.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class SpringTimetableControllerTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";
    private static final String ID = "ID";

    @InjectMocks
    private SpringTimetableController springTimetableController;
    @Mock
    private TimetableController timetableController;

    @Test
    void findByFlightIdentifierTest_scheduledFlightWithGivenIdentifierNotExists() {
        when(timetableController.findByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.empty());

        given()
                .standaloneSetup(springTimetableController)
        .when()
                .get("/timetable/" + FLIGHT_IDENTIFIER)
        .then()
                .status(NOT_FOUND);
    }

    @Test
    void findByFlightIdentifierTest_scheduledFlightWithGivenIdentifierExists() {
        ScheduledFlightWeb scheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ID)
                .build();

        when(timetableController.findByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Optional.of(scheduledFlightWeb));

        given()
                .standaloneSetup(springTimetableController)
                .when()
                .get("/timetable/" + FLIGHT_IDENTIFIER)
                .then()
                .status(OK)
                .contentType(JSON)
                .assertThat()
                .body("id", equalTo(ID));
    }
}