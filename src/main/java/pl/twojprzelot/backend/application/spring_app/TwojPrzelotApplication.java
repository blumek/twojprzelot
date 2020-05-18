package pl.twojprzelot.backend.application.spring_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import picocli.CommandLine;
import pl.twojprzelot.backend.application.spring_app.command.BaseCommand;

@SpringBootApplication
@EntityScan("pl.twojprzelot.backend.adapter.repository.database")
@EnableJpaRepositories("pl.twojprzelot.backend.adapter.repository.database")
@ComponentScan("pl.twojprzelot.backend")
public class TwojPrzelotApplication {
    private static final BaseCommand baseCommand = new BaseCommand();

    @Bean
    BaseCommand baseCommand() {
        return baseCommand;
    }

    public static void main(String[] args) {
        parseArgs(args);
        SpringApplication.run(TwojPrzelotApplication.class, args);
    }

    private static void parseArgs(String[] args) {
        new CommandLine(baseCommand).parseArgs(args);
    }
}
