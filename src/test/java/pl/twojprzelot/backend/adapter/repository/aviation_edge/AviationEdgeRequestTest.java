package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AviationEdgeRequestTest {
    private static final String URL = "URL";
    private static final String API_KEY = "API_KEY";
    private static final String BASE_URL = "URL?key=API_KEY";
    private static final String URL_WITH_QUERY_PARAMS = "URL?key=API_KEY&Q_KEY=Q_VALUE";
    private static final String NO_RESOURCES = "NO_RESOURCES";
    private static final String RESOURCES = "RESOURCES";
    private static final String FIRST_RESOURCE = "ONE";
    private static final String SECOND_RESOURCE = "TWO";
    private static final String QUERY_KEY = "Q_KEY";
    private static final String QUERY_VALUE = "Q_VALUE";
    private static final Class<String> REQUESTED_CLASS = String.class;
    private static final Class<String[]> REQUESTED_ARRAY_CLASS = String[].class;

    AviationEdgeRequest<String> request;
    @Mock
    RestTemplate restTemplate;
    @Mock
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        request = new AviationEdgeRequest<>(REQUESTED_ARRAY_CLASS, URL, API_KEY, restTemplate, objectMapper);
    }

    @Test
    @SneakyThrows
    void getTest_resourcesNotAvailable_nullReturnedFromRequest() {
        when(restTemplate.getForObject(BASE_URL, REQUESTED_CLASS))
                .thenReturn(null);

        List<String> requestedResources = request.get();
        assertTrue(requestedResources.isEmpty());

        verify(restTemplate).getForObject(BASE_URL, REQUESTED_CLASS);
        verify(objectMapper, never()).readValue((String) null, REQUESTED_ARRAY_CLASS);
    }

    @Test
    @SneakyThrows
    void getTest_resourcesNotAvailable_noResourcesReturnedFromRequest() {
        when(restTemplate.getForObject(BASE_URL, REQUESTED_CLASS))
                .thenReturn(NO_RESOURCES);

        when(objectMapper.readValue(NO_RESOURCES, REQUESTED_ARRAY_CLASS))
                .thenReturn(new String[]{});

        List<String> requestedResources = request.get();
        assertTrue(requestedResources.isEmpty());

        verify(restTemplate).getForObject(BASE_URL, REQUESTED_CLASS);
        verify(objectMapper).readValue(NO_RESOURCES, REQUESTED_ARRAY_CLASS);
    }

    @Test
    void getTest_serverError() {
        when(restTemplate.getForObject(BASE_URL, REQUESTED_CLASS))
                .thenThrow(HttpServerErrorException.class);

        List<String> requestedResources = request.get();
        assertTrue(requestedResources.isEmpty());

        verify(restTemplate).getForObject(BASE_URL, REQUESTED_CLASS);
    }

    @Test
    @SneakyThrows
    void getTest_twoResourcesAvailable() {
        when(restTemplate.getForObject(BASE_URL, REQUESTED_CLASS))
                .thenReturn(RESOURCES);

        when(objectMapper.readValue(RESOURCES, REQUESTED_ARRAY_CLASS))
                .thenReturn(new String[]{FIRST_RESOURCE, SECOND_RESOURCE});

        List<String> requestedResources = request.get();
        assertThat(requestedResources, containsInAnyOrder(FIRST_RESOURCE, SECOND_RESOURCE));

        verify(restTemplate).getForObject(BASE_URL, REQUESTED_CLASS);
        verify(objectMapper).readValue(RESOURCES, REQUESTED_ARRAY_CLASS);
    }

    @Test
    void getTest_withoutQueryParams() {
        request.get();

        assertEquals(BASE_URL, request.getRequestUrl());
        assertTrue(request.getQueryParams().isEmpty());

        verify(restTemplate).getForObject(BASE_URL, REQUESTED_CLASS);
    }

    @Test
    void getTest_withQueryParams() {
        request.addQueryParameter(QUERY_KEY, QUERY_VALUE);

        request.get();

        assertEquals(URL_WITH_QUERY_PARAMS, request.getRequestUrl());
        assertThat(request.getQueryParams(), hasEntry(QUERY_KEY, QUERY_VALUE));

        verify(restTemplate).getForObject(URL_WITH_QUERY_PARAMS, REQUESTED_CLASS);
    }
}