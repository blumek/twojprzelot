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
class AirlineRequestTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final String IATA_CODE = "IATA_CODE";
    private static final String BASIC_REQUEST_URL = "BASE_URL/airlineDatabase?key=API_KEY";
    private static final String REQUEST_URL_WITH_PARAMS = "BASE_URL/airlineDatabase?key=API_KEY&codeIataAirline=IATA_CODE";
    private static final int FIRST_ID = 1;
    private static final int SECOND_ID = 2;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void getTest_noResults_emptyListReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, AirlineAE[].class))
                .thenReturn(new AirlineAE[]{});

        AirlineRequest request = new AirlineRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<AirlineAE> airlines = request.get();

        assertTrue(airlines.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, AirlineAE[].class);
    }

    @Test
    void getTest_noResults_nullReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, AirlineAE[].class))
                .thenReturn(null);

        AirlineRequest request = new AirlineRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<AirlineAE> airlines = request.get();

        assertTrue(airlines.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, AirlineAE[].class);
    }

    @Test
    void getTest_twoResults() {
        AirlineAE firstAirlineAE = new AirlineAE();
        firstAirlineAE.setId(FIRST_ID);

        AirlineAE secondAirlineAE = new AirlineAE();
        secondAirlineAE.setId(SECOND_ID);

        when(restTemplate.getForObject(BASIC_REQUEST_URL, AirlineAE[].class))
                .thenReturn(new AirlineAE[]{firstAirlineAE, secondAirlineAE});

        AirlineRequest request = new AirlineRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        assertThat(request.get(), containsInAnyOrder(firstAirlineAE, secondAirlineAE));

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, AirlineAE[].class);
    }

    @Test
    void getTest_internalServerError() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, AirlineAE[].class))
                .thenThrow(HttpServerErrorException.InternalServerError.class);

        AirlineRequest request = new AirlineRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<AirlineAE> airlines = request.get();
        assertTrue(airlines.isEmpty());

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, AirlineAE[].class);
    }

    @Test
    void getTest_queryParams_iataCode() {
        AirlineRequest request = new AirlineRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .iataCode(IATA_CODE)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("codeIataAirline", IATA_CODE));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_PARAMS, AirlineAE[].class);
    }

    @Test
    void getTest_queryParams_iataCode_null() {
        assertThrows(NullPointerException.class, () ->
                new AirlineRequest.Builder(BASE_URL, API_KEY, restTemplate).iataCode(null));
    }

    @Test
    void getTest_queryParams_iataCode_blank() {
        assertThrows(IllegalArgumentException.class, () ->
                new AirlineRequest.Builder(BASE_URL, API_KEY, restTemplate).iataCode(" "));
    }
}