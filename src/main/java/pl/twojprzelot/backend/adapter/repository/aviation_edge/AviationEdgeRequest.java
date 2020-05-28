package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PACKAGE;

@Slf4j
@Getter
@EqualsAndHashCode(exclude = {"restTemplate", "objectMapper"})
@RequiredArgsConstructor(access = PACKAGE)
class AviationEdgeRequest<T> {
    private static final String API_KEY = "key";

    @Getter(NONE)
    private final Class<T[]> resourceArrayType;
    private final String url;
    private final String apiKey;
    @Getter(NONE)
    private final RestTemplate restTemplate;
    @Getter(NONE)
    private final ObjectMapper objectMapper;
    private final Map<String, String> queryParams = new HashMap<>();

    List<T> get() {
        String requestUrl = getRequestUrl();
        return request(requestUrl);
    }

    String getRequestUrl() {
        StringBuilder urlBuilder = new StringBuilder(url)
                .append("?");
        appendApiKey(urlBuilder);
        appendQueryParams(urlBuilder);

        return urlBuilder.toString();
    }

    private void appendApiKey(StringBuilder urlBuilder) {
        urlBuilder.append(API_KEY)
                .append("=")
                .append(apiKey);
    }

    private void appendQueryParams(StringBuilder urlBuilder) {
        for (Map.Entry<String, String> param : queryParams.entrySet())
            appendQueryParam(urlBuilder, param.getKey(), param.getValue());
    }

    private void appendQueryParam(StringBuilder urlBuilder, String key, String value) {
        urlBuilder.append("&")
                .append(key)
                .append("=")
                .append(value);
    }

    private List<T> request(String requestUrl) {
        T[] requestedResources = getRequestedResources(requestUrl);

        if (requestedResources == null)
            return Lists.newArrayList();

        return Arrays.asList(requestedResources);
    }

    private T[] getRequestedResources(String requestUrl) {
        T[] requestedResources = null;

        try {
            String requestedResourcesResponse = restTemplate.getForObject(requestUrl, String.class);
            if (requestedResourcesResponse != null)
                requestedResources = parseToRequestedResources(requestedResourcesResponse);

        } catch (HttpServerErrorException exception) {
            log.error(exception.getMessage());
        }

        return requestedResources;
    }

    private T[] parseToRequestedResources(String requestedResourcesResponse) {
        T[] requestedResources = null;

        try {
            requestedResources = objectMapper.readValue(requestedResourcesResponse, resourceArrayType);
        } catch (JsonProcessingException exception) {
            ErrorAE error = parseErrorResponse(requestedResourcesResponse);
            log.info(format("Aviation Edge API response message: %s", error.getMessage()));
        }

        return requestedResources;
    }

    private ErrorAE parseErrorResponse(String requestedResourcesResponse) {
        ErrorAE error = new ErrorAE();

        try {
            error = objectMapper.readValue(requestedResourcesResponse, ErrorAE.class);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
        }

        return error;
    }

    AviationEdgeRequest<T> addQueryParameter(@NonNull String key, @NonNull String value) {
        queryParams.put(key, value);
        return this;
    }

    AviationEdgeRequest<T> removeQueryParameter(@NonNull String key) {
        queryParams.remove(key);
        return this;
    }
}
