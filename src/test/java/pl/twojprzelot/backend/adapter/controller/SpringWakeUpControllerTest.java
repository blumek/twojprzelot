package pl.twojprzelot.backend.adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class SpringWakeUpControllerTest {
    private SpringWakeUpController springWakeUpController;

    @BeforeEach
    void setUp() {
        springWakeUpController = new SpringWakeUpController();
    }

    @Test
    void wakeUpTest() {
        given()
                .standaloneSetup(springWakeUpController)
        .when()
                .get("/wake-up")
        .then()
                .status(OK);
    }
}