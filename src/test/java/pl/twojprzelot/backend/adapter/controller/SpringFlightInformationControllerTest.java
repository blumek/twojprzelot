package pl.twojprzelot.backend.adapter.controller;

import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.ERROR;
import static pl.twojprzelot.backend.adapter.controller.ResponseWeb.Status.SUCCESS;


@ExtendWith(MockitoExtension.class)
class SpringFlightInformationControllerTest {
    private static final String FLIGHT_IDENTIFIER = "FLIGHT_IDENTIFIER";

    @InjectMocks
    private SpringFlightInformationController springFlightInformationController;

    @Mock
    private FlightInformationController flightInformationController;

    @Test
    void findCurrentByFlightIdentifier_noFlightAvailable() {
        when(flightInformationController.findCurrentByFlightIdentifier(anyString()))
                .thenReturn(Optional.empty());

        ResponseWeb<FlightInformationWeb> expectedResponse = ResponseWeb.<FlightInformationWeb>builder()
                .status(ERROR)
                .message("Flight information with given ID not found")
                .build();

        ResponseWeb<FlightInformationWeb> foundFlightInformation = given()
                    .standaloneSetup(springFlightInformationController)
                .when()
                    .get("/flight-information/" + FLIGHT_IDENTIFIER)
                .then()
                    .contentType(JSON)
                    .status(NOT_FOUND)
                    .extract()
                    .as(new TypeRef<>() {});

        assertEquals(expectedResponse, foundFlightInformation);
    }

    @Test
    void findCurrentByFlightIdentifier_flightAvailable() {
        FlightInformationWeb flightInformation = FlightInformationWeb.from(
                FlightWeb.builder().build(),
                ScheduledFlightWeb.builder().build()
        );

        when(flightInformationController.findCurrentByFlightIdentifier(anyString()))
                .thenReturn(Optional.of(flightInformation));

        ResponseWeb<FlightInformationWeb> expectedResponse = ResponseWeb.<FlightInformationWeb>builder()
                .status(SUCCESS)
                .data(flightInformation)
                .build();

        ResponseWeb<FlightInformationWeb> foundFlightInformation = given()
                .standaloneSetup(springFlightInformationController)
                .when()
                .get("/flight-information/" + FLIGHT_IDENTIFIER)
                .then()
                .contentType(JSON)
                .status(OK)
                .extract()
                .as(new TypeRef<>() {});

        assertEquals(expectedResponse, foundFlightInformation);
    }
}