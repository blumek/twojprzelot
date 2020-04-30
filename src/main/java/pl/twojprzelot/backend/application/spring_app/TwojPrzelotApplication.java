package pl.twojprzelot.backend.application.spring_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("pl.twojprzelot.backend.adapter.repository.database.model")
@EnableJpaRepositories("pl.twojprzelot.backend.adapter.repository")
public class TwojPrzelotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwojPrzelotApplication.class, args);
    }
}
