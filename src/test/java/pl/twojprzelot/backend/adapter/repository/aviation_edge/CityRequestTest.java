package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityRequestTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String BASIC_REQUEST_URL = "BASE_URL/cityDatabase?key=API_KEY";
    private static final String REQUEST_URL_WITH_PARAMS = "BASE_URL/cityDatabase?key=API_KEY&codeIataCity=IATA_CODE";
    private static final int FIRST_ID = 1;
    private static final int SECOND_ID = 2;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void getTest_noResults_emptyListReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, CityAE[].class))
                .thenReturn(new CityAE[]{});

        CityRequest request = new CityRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<CityAE> cities = request.get();

        assertTrue(cities.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CityAE[].class);
    }

    @Test
    void getTest_noResults_nullReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, CityAE[].class))
                .thenReturn(null);

        CityRequest request = new CityRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<CityAE> cities = request.get();

        assertTrue(cities.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CityAE[].class);
    }

    @Test
    void getTest_twoResults() {
        CityAE firstCityAE = new CityAE();
        firstCityAE.setId(FIRST_ID);

        CityAE secondCityAE = new CityAE();
        secondCityAE.setId(SECOND_ID);

        when(restTemplate.getForObject(BASIC_REQUEST_URL, CityAE[].class))
                .thenReturn(new CityAE[]{firstCityAE, secondCityAE});

        CityRequest request = new CityRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        assertThat(request.get(), containsInAnyOrder(firstCityAE, secondCityAE));

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CityAE[].class);
    }

    @Test
    void getTest_internalServerError() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, CityAE[].class))
                .thenThrow(HttpServerErrorException.InternalServerError.class);

        CityRequest request = new CityRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<CityAE> cities = request.get();
        assertTrue(cities.isEmpty());

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CityAE[].class);
    }

    @Test
    void getTest_queryParams_iataCode() {
        CityRequest request = new CityRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .iataCode(IATA_CODE)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("codeIataCity", IATA_CODE));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_PARAMS, CityAE[].class);
    }

    @Test
    void getTest_queryParams_iataCode_null() {
        assertThrows(NullPointerException.class, () ->
                new CityRequest.Builder(BASE_URL, API_KEY, restTemplate).iataCode(null));
    }

    @Test
    void getTest_queryParams_iataCode_blank() {
        assertThrows(IllegalArgumentException.class, () ->
                new CityRequest.Builder(BASE_URL, API_KEY, restTemplate).iataCode(" "));
    }
}