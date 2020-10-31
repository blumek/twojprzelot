package pl.twojprzelot.backend.application.spring_app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(args = {
        "--aviationEdgeKey=aviationEdgeKey",
        "--applicationKey=applicationKey"
})
class TwojPrzelotApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
