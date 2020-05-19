package pl.twojprzelot.backend.adapter.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.SUCCESS;

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
                .status(SUCCESS)
                .data(Lists.newArrayList())
                .build();

        ResponseWeb<List<ScheduledFlightWeb>> foundScheduledFlights = given()
                .standaloneSetup(springTimetableController)
        .when()
                .get("/timetable/" + FLIGHT_IDENTIFIER)
        .then()
                .contentType(JSON)
                .status(OK)
                .extract()
                .as(new ParameterizedTypeReference<ResponseWeb<List<ScheduledFlightWeb>>>() {}.getType());

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
                .as(new ParameterizedTypeReference<ResponseWeb<List<ScheduledFlightWeb>>>() {}.getType());

        assertEquals(expectedResponse, foundScheduledFlights);
    }
}