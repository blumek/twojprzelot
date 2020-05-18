package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AviationEdgeRepositoryConfiguration {

    @Bean
    public AviationEdgeClient aviationEdgeClient(@Value("${aviationEdgeKey}") String aviationEdgeKey) {
        return new AviationEdgeClient(aviationEdgeKey);
    }
}
