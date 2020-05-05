package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Country;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryAERepositoryTest {
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final int ID = 1;

    @InjectMocks
    private CountryAERepository countryAERepository;
    @Mock
    private AviationEdgeClient aviationEdgeClient;
    @Mock
    private CountryRequest.Builder countryRequestBuilder;
    @Mock
    private CountryRequest countryRequest;

    @BeforeEach
    void setUp() {
        when(aviationEdgeClient.createCountryRequest())
                .thenReturn(countryRequestBuilder);
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

        verify(countryRequest).get();
    }

    @Test
    void findByIso2CodeTest_countryWithGivenIso2CodeExists() {
        CountryAE countryAE = new CountryAE();
        countryAE.setId(ID);

        when(countryRequestBuilder.iso2Code(ISO_2_CODE))
                .thenReturn(countryRequestBuilder);

        when(countryRequestBuilder.create())
                .thenReturn(countryRequest);

        when(countryRequest.get())
                .thenReturn(Lists.newArrayList(countryAE));

        Country expectedCountry = Country.builder()
                .id(ID)
                .build();

        assertEquals(Optional.of(expectedCountry), countryAERepository.findByIso2Code(ISO_2_CODE));

        verify(countryRequest).get();
    }
}