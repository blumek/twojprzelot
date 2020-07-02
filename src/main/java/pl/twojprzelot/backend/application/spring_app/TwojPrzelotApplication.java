package pl.twojprzelot.backend.application.spring_app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.twojprzelot.backend.usecase.*;

@Slf4j
@SpringBootApplication
@EntityScan("pl.twojprzelot.backend.adapter.repository.database")
@EnableJpaRepositories("pl.twojprzelot.backend.adapter.repository.database")
@ComponentScan("pl.twojprzelot.backend")
@RequiredArgsConstructor
public class TwojPrzelotApplication implements CommandLineRunner {
    private final ImportStaticData importStaticData;

    public static void main(String[] args) {
        SpringApplication.run(TwojPrzelotApplication.class, args);
    }

    @Override
    public void run(String... args) {
        importStaticData.overrideAll();
    }

}