package pl.twojprzelot.backend.application.spring_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("pl.twojprzelot.backend.adapter.repository.model")
public class TwojPrzelotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwojPrzelotApplication.class, args);
    }
}
