package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
@EqualsAndHashCode(exclude = "restTemplate")
abstract class Request<T> {
    final Map<String, String> queryParams = new HashMap<>();
    private final Class<T[]> resourceArrayType;
    private final String url;
    private final String apiKey;
    private final RestTemplate restTemplate;

    public List<T> get() {
        String requestUrl = createRequestUrl();
        return request(requestUrl);
    }

    private List<T> request(String requestUrl) {
        T[] requestedResources = restTemplate.getForObject(requestUrl, resourceArrayType);
        if (requestedResources == null)
            return Lists.newArrayList();

        return Arrays.asList(requestedResources);
    }

    private String createRequestUrl() {
        StringBuilder urlBuilder = new StringBuilder();
        createUrlBase(urlBuilder);
        appendQueryParams(urlBuilder);

        return urlBuilder.toString();
    }

    private void createUrlBase(StringBuilder urlBuilder) {
        urlBuilder.append(url)
                .append("?key=")
                .append(apiKey);
    }

    private void appendQueryParams(StringBuilder urlBuilder) {
        for (Map.Entry<String, String> param : queryParams.entrySet()) {
            urlBuilder.append("&")
                    .append(param.getKey())
                    .append("=")
                    .append(param.getValue());
        }
    }
}
