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
class CountryRequestTest {
    private static final String BASE_URL = "BASE_URL";
    private static final String API_KEY = "API_KEY";
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final String BASIC_REQUEST_URL = "BASE_URL/countryDatabase?key=API_KEY";
    private static final String REQUEST_URL_WITH_PARAMS = "BASE_URL/countryDatabase?key=API_KEY&codeIso2Country=ISO_2_CODE";
    private static final int FIRST_ID = 1;
    private static final int SECOND_ID = 2;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void getTest_noResults_emptyListReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, CountryAE[].class))
                .thenReturn(new CountryAE[]{});

        CountryRequest request = new CountryRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<CountryAE> countries = request.get();

        assertTrue(countries.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CountryAE[].class);
    }

    @Test
    void getTest_noResults_nullReturned() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, CountryAE[].class))
                .thenReturn(null);

        CountryRequest request = new CountryRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<CountryAE> countries = request.get();

        assertTrue(countries.isEmpty());
        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CountryAE[].class);
    }

    @Test
    void getTest_twoResults() {
        CountryAE firstCountryAE = new CountryAE();
        firstCountryAE.setId(FIRST_ID);

        CountryAE secondCountryAE = new CountryAE();
        secondCountryAE.setId(SECOND_ID);

        when(restTemplate.getForObject(BASIC_REQUEST_URL, CountryAE[].class))
                .thenReturn(new CountryAE[]{firstCountryAE, secondCountryAE});

        CountryRequest request = new CountryRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        assertThat(request.get(), containsInAnyOrder(firstCountryAE, secondCountryAE));

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CountryAE[].class);
    }

    @Test
    void getTest_internalServerError() {
        when(restTemplate.getForObject(BASIC_REQUEST_URL, CountryAE[].class))
                .thenThrow(HttpServerErrorException.InternalServerError.class);

        CountryRequest request = new CountryRequest.Builder(BASE_URL, API_KEY, restTemplate).create();

        List<CountryAE> countries = request.get();
        assertTrue(countries.isEmpty());

        verify(restTemplate).getForObject(BASIC_REQUEST_URL, CountryAE[].class);
    }

    @Test
    void getTest_queryParams_iso2Code() {
        CountryRequest request = new CountryRequest.Builder(BASE_URL, API_KEY, restTemplate)
                .iso2Code(ISO_2_CODE)
                .create();

        request.get();

        assertThat(request.queryParams, hasEntry("codeIso2Country", ISO_2_CODE));
        verify(restTemplate).getForObject(REQUEST_URL_WITH_PARAMS, CountryAE[].class);
    }

    @Test
    void getTest_queryParams_iso2Code_null() {
        assertThrows(NullPointerException.class, () ->
                new CountryRequest.Builder(BASE_URL, API_KEY, restTemplate).iso2Code(null));
    }

    @Test
    void getTest_queryParams_iso2Code_blank() {
        assertThrows(IllegalArgumentException.class, () ->
                new CountryRequest.Builder(BASE_URL, API_KEY, restTemplate).iso2Code(" "));
    }
}