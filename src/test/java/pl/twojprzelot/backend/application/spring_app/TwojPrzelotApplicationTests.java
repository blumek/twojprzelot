package pl.twojprzelot.backend.application.spring_app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = {
        "--aviationEdgeKey=aviationEdgeKey",
        "--applicationKey=applicationKey"
})
class TwojPrzelotApplicationTests {

    @Test
    void contextLoads() {
    }

}
