package pl.twojprzelot.backend.application.spring_app.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.adapter.controller.TimetableController;
import pl.twojprzelot.backend.adapter.controller.model.ScheduledFlightWeb;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class SpringTimetableControllerTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";
    private static final String ID = "ID";
    private static final String ANOTHER_ID = "ANOTHER_ID";

    @InjectMocks
    private SpringTimetableController springTimetableController;
    @Mock
    private TimetableController timetableController;

    @Test
    void findAllByFlightIdentifierTest_scheduledFlightsWithGivenIdentifierNotExist() {
        when(timetableController.findAllByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList());

        given()
                .standaloneSetup(springTimetableController)
        .when()
                .get("/timetable/" + FLIGHT_IDENTIFIER)
        .then()
                .contentType(JSON)
                .status(OK)
                .body(is(equalTo("[]")));
    }

    @Test
    void findAllByFlightIdentifierTest_twoScheduledFlightsWithGivenIdentifierExist() {
        ScheduledFlightWeb firstScheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ID)
                .build();

        ScheduledFlightWeb secondScheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ANOTHER_ID)
                .build();

        when(timetableController.findAllByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList(firstScheduledFlightWeb, secondScheduledFlightWeb));

        ScheduledFlightWeb[] foundScheduledFlights = given()
                .standaloneSetup(springTimetableController)
        .when()
                .get("/timetable/" + FLIGHT_IDENTIFIER)
        .then()
                .contentType(JSON)
                .status(OK)
                .extract()
                .as(ScheduledFlightWeb[].class);

        assertThat(Lists.newArrayList(foundScheduledFlights),
                containsInAnyOrder(firstScheduledFlightWeb, secondScheduledFlightWeb));
    }
}