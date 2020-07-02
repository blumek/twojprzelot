package pl.twojprzelot.backend.application.spring_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EntityScan("pl.twojprzelot.backend.adapter.repository.database")
@EnableJpaRepositories("pl.twojprzelot.backend.adapter.repository.database")
@ComponentScan("pl.twojprzelot.backend")
public class TwojPrzelotApplication{

    public static void main(String[] args) {
        SpringApplication.run(TwojPrzelotApplication.class, args);
    }

}