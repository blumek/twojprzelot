package pl.twojprzelot.backend.application.spring_app.command;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class CommandConfiguration {
    private final BaseCommand baseCommand;

    @Bean("aviationEdgeKey")
    String aviationEdgeKey() {
        return baseCommand.getAviationEdgeKey();
    }
}
