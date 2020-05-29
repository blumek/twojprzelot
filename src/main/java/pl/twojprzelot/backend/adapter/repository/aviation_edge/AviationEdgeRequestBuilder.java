package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;


abstract class AviationEdgeRequestBuilder<T> {
    final AviationEdgeRequest<T> request;

    AviationEdgeRequestBuilder(Class<T[]> classType, String baseUrl, String apiKey, RestTemplate restTemplate,
                               ObjectMapper objectMapper) {
        request = new AviationEdgeRequest<>(classType, getResourceUrl(baseUrl), apiKey, restTemplate, objectMapper);
    }

    abstract String getResourceUrl(String baseUrl);

    public AviationEdgeRequest<T> build() {
        return request;
    }
}
