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
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.ERROR;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.SUCCESS;

@ExtendWith(MockitoExtension.class)
class SpringFlightControllerTest {
    private static final String NUMBER = "NUMBER";
    private static final String ANOTHER_NUMBER = "ANOTHER_NUMBER";

    @InjectMocks
    private SpringFlightController springFlightController;
    @Mock
    private FlightController flightController;

    @Test
    void findAllTest_noFlightsAvailable() {
        when(flightController.findAll())
                .thenReturn(Lists.newArrayList());

        ResponseWeb<List<FlightWeb>> expectedResponse = ResponseWeb.<List<FlightWeb>>builder()
                .status(ERROR)
                .data(Lists.newArrayList())
                .message("No available flights")
                .build();

        ResponseWeb<List<FlightWeb>> foundFlights = given()
                .standaloneSetup(springFlightController)
        .when()
                .get("/flights")
        .then()
                .contentType(JSON)
                .status(NOT_FOUND)
                .extract()
                .as(new ParameterizedTypeReference<ResponseWeb<List<FlightWeb>>>() {}.getType());

        assertEquals(expectedResponse, foundFlights);
    }

    @Test
    void findAllTest_twoFlightsAvailable() {
        FlightWeb firstFlightWeb = FlightWeb.builder()
                .flightIdentifier(FlightIdentifierWeb.builder()
                        .number(NUMBER)
                        .build())
                .build();

        FlightWeb secondFlightWeb = FlightWeb.builder()
                .flightIdentifier(FlightIdentifierWeb.builder()
                        .number(ANOTHER_NUMBER)
                        .build())
                .build();

        ResponseWeb<List<FlightWeb>> expectedResponse = ResponseWeb.<List<FlightWeb>>builder()
                .status(SUCCESS)
                .data(Lists.newArrayList(firstFlightWeb, secondFlightWeb))
                .build();

        when(flightController.findAll())
                .thenReturn(Lists.newArrayList(firstFlightWeb, secondFlightWeb));

        ResponseWeb<List<FlightWeb>> foundFlights = given()
                .standaloneSetup(springFlightController)
        .when()
                .get("/flights")
        .then()
                .contentType(JSON)
                .status(OK)
                .extract()
                .as(new ParameterizedTypeReference<ResponseWeb<List<FlightWeb>>>() {}.getType());

        assertEquals(expectedResponse, foundFlights);
    }
}