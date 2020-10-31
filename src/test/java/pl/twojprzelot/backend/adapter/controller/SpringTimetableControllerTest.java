package pl.twojprzelot.backend.adapter.controller;

import io.restassured.mapper.TypeRef;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static io.restassured.http.ContentType.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.*;

@ExtendWith(MockitoExtension.class)
class SpringTimetableControllerTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private SpringTimetableController springTimetableController;
    @Mock
    private TimetableController timetableController;

    @Test
    void findAllByFlightIdentifierTest_scheduledFlightsWithGivenIdentifierNotExist() {
        when(timetableController.findAllByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList());

        ResponseWeb<List<ScheduledFlightWeb>> expectedResponse = ResponseWeb.<List<ScheduledFlightWeb>>builder()
                .status(ERROR)
                .data(Lists.newArrayList())
                .message("Flight with given ID not found")
                .build();

        ResponseWeb<List<ScheduledFlightWeb>> foundScheduledFlights = given()
                .standaloneSetup(springTimetableController)
        .when()
                .get("/timetable/" + FLIGHT_IDENTIFIER)
        .then()
                .contentType(JSON)
                .status(NOT_FOUND)
                .extract()
                .as(new TypeRef<>() {});

        assertEquals(expectedResponse, foundScheduledFlights);
    }

    @Test
    void findAllByFlightIdentifierTest_twoScheduledFlightsWithGivenIdentifierExist() {
        ScheduledFlightWeb firstScheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ID)
                .build();

        ScheduledFlightWeb secondScheduledFlightWeb = ScheduledFlightWeb.builder()
                .id(ANOTHER_ID)
                .build();

        ResponseWeb<List<ScheduledFlightWeb>> expectedResponse = ResponseWeb.<List<ScheduledFlightWeb>>builder()
                .status(SUCCESS)
                .data(Lists.newArrayList(firstScheduledFlightWeb, secondScheduledFlightWeb))
                .build();

        when(timetableController.findAllByFlightIdentifier(FLIGHT_IDENTIFIER))
                .thenReturn(Lists.newArrayList(firstScheduledFlightWeb, secondScheduledFlightWeb));

        ResponseWeb<List<ScheduledFlightWeb>> foundScheduledFlights = given()
                .standaloneSetup(springTimetableController)
        .when()
                .get("/timetable/" + FLIGHT_IDENTIFIER)
        .then()
                .contentType(JSON)
                .status(OK)
                .extract()
                .as(new TypeRef<>() {});

        assertEquals(expectedResponse, foundScheduledFlights);
    }
}