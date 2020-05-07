package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Country;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryAERepositoryTest {
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private CountryAERepository countryAERepository;
    @Mock
    private AviationEdgeClient aviationEdgeClient;
    @Mock
    private CountryRequest.Builder countryRequestBuilder;
    @Mock
    private CountryRequest countryRequest;

    private CountryAE countryAE;
    private CountryAE anotherCountryAE;
    private Country expectedCountry;
    private Country anotherExpectedCountry;

    @BeforeEach
    void setUp() {
        when(aviationEdgeClient.createCountryRequest())
                .thenReturn(countryRequestBuilder);

        countryAE = new CountryAE();
        countryAE.setId(ID);

        anotherCountryAE = new CountryAE();
        anotherCountryAE.setId(ANOTHER_ID);

        expectedCountry = Country.builder()
                .id(ID)
                .build();

        anotherExpectedCountry = Country.builder()
                .id(ANOTHER_ID)
                .build();
    }

    @Test
    void findByIso2CodeTest_countryWithGivenIso2CodeNotExists() {
        when(countryRequestBuilder.iso2Code(ISO_2_CODE))
                .thenReturn(countryRequestBuilder);

        when(countryRequestBuilder.create())
                .thenReturn(countryRequest);

        when(countryRequest.get())
                .thenReturn(Lists.newArrayList());

        assertEquals(Optional.empty(), countryAERepository.findByIso2Code(ISO_2_CODE));

        verify(countryRequestBuilder).iso2Code(ISO_2_CODE);
        verify(countryRequest).get();
    }

    @Test
    void findByIso2CodeTest_countryWithGivenIso2CodeExists() {
        when(countryRequestBuilder.iso2Code(ISO_2_CODE))
                .thenReturn(countryRequestBuilder);

        when(countryRequestBuilder.create())
                .thenReturn(countryRequest);

        when(countryRequest.get())
                .thenReturn(Lists.newArrayList(countryAE, anotherCountryAE));

        assertEquals(Optional.of(expectedCountry), countryAERepository.findByIso2Code(ISO_2_CODE));

        verify(countryRequestBuilder).iso2Code(ISO_2_CODE);
        verify(countryRequest).get();
    }

    @Test
    void findAllTest_noCountriesAvailable() {
        when(countryRequestBuilder.create())
                .thenReturn(countryRequest);

        when(countryRequest.get())
                .thenReturn(Lists.newArrayList());


        List<Country> foundCountries = countryAERepository.findAll();
        assertTrue(foundCountries.isEmpty());

        verify(countryRequest).get();
    }

    @Test
    void findAllTest_twoCountriesAvailable() {
        when(countryRequestBuilder.create())
                .thenReturn(countryRequest);

        when(countryRequest.get())
                .thenReturn(Lists.newArrayList(countryAE, anotherCountryAE));

        List<Country> foundCountries = countryAERepository.findAll();
        assertThat(foundCountries, containsInAnyOrder(expectedCountry, anotherExpectedCountry));

        verify(countryRequest).get();
    }
}