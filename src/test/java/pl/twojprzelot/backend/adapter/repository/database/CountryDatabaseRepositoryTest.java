package pl.twojprzelot.backend.adapter.repository.database;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDatabaseRepositoryTest {
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final int ID = 1;
    private static final int ANOTHER_ID = 2;

    @InjectMocks
    private CountryDatabaseRepository countryDatabaseRepository;
    @Mock
    private CountrySpringRepository countrySpringRepository;

    private CountryEntity countryEntity;
    private Country expectedCountry;

    @BeforeEach
    void setUp() {
        countryEntity = new CountryEntity();
        countryEntity.setId(ID);
        countryEntity.setIso2Code(ISO_2_CODE);

        expectedCountry = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .build();
    }

    @Test
    void findByIso2CodeTest_countryWithGivenIso2CodeExists() {
        when(countrySpringRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.empty());

        Optional<Country> foundCountry = countryDatabaseRepository.findByIso2Code(ISO_2_CODE);
        assertEquals(Optional.empty(), foundCountry);

        verify(countrySpringRepository).findByIso2Code(ISO_2_CODE);
    }

    @Test
    void findByIso2CodeTest_countryWithGivenIso2CodeNotExists() {
        when(countrySpringRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.of(countryEntity));

        Optional<Country> foundCountry = countryDatabaseRepository.findByIso2Code(ISO_2_CODE);
        assertEquals(Optional.of(expectedCountry), foundCountry);

        verify(countrySpringRepository).findByIso2Code(ISO_2_CODE);
    }

    @Test
    void findAllTest_noCountriesAvailable() {
        when(countrySpringRepository.findAll())
                .thenReturn(Lists.newArrayList());

        List<Country> foundCountries = countryDatabaseRepository.findAll();
        assertTrue(foundCountries.isEmpty());

        verify(countrySpringRepository).findAll();
    }

    @Test
    void findAllTest_twoCountriesAvailable() {
        CountryEntity anotherCountryEntity = new CountryEntity();
        anotherCountryEntity.setId(ANOTHER_ID);
        anotherCountryEntity.setIso2Code(ISO_2_CODE);

        when(countrySpringRepository.findAll())
                .thenReturn(Lists.newArrayList(countryEntity, anotherCountryEntity));

        Country anotherExpectedCountry = Country.builder()
                .id(ANOTHER_ID)
                .iso2Code(ISO_2_CODE)
                .build();

        List<Country> foundCountries = countryDatabaseRepository.findAll();
        assertThat(foundCountries, containsInAnyOrder(expectedCountry, anotherExpectedCountry));

        verify(countrySpringRepository).findAll();
    }
}