package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class AviationEdgeRepositoryConfiguration {

    @Bean
    public AviationEdgeClient aviationEdgeClient(@Value("${aviationEdgeKey}") String aviationEdgeKey,
                                                 RestTemplate restTemplate,
                                                 ObjectMapper objectMapper) {
        return new AviationEdgeClient(aviationEdgeKey, restTemplate, objectMapper);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
