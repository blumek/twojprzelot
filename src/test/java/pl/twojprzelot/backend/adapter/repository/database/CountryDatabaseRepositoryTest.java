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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    private CountryEntity anotherCountryEntity;
    private Country country;
    private Country anotherCountry;

    @BeforeEach
    void setUp() {
        countryEntity = new CountryEntity();
        countryEntity.setId(ID);
        countryEntity.setIso2Code(ISO_2_CODE);

        anotherCountryEntity = new CountryEntity();
        anotherCountryEntity.setId(ANOTHER_ID);
        anotherCountryEntity.setIso2Code(ISO_2_CODE);

        country = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .build();

        anotherCountry = Country.builder()
                .id(ANOTHER_ID)
                .iso2Code(ISO_2_CODE)
                .build();
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
        when(countrySpringRepository.findAll())
                .thenReturn(Lists.newArrayList(countryEntity, anotherCountryEntity));

        List<Country> foundCountries = countryDatabaseRepository.findAll();
        assertThat(foundCountries, containsInAnyOrder(country, anotherCountry));

        verify(countrySpringRepository).findAll();
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
        assertEquals(Optional.of(country), foundCountry);

        verify(countrySpringRepository).findByIso2Code(ISO_2_CODE);
    }

    @Test
    void findByIso2CodeTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> countryDatabaseRepository.findByIso2Code(null));

        verify(countrySpringRepository, never()).findByIso2Code(null);
    }

    @Test
    void createTest() {
        when(countrySpringRepository.save(countryEntity))
                .thenReturn(anotherCountryEntity);

        Country createdCountry = countryDatabaseRepository.create(country);
        assertEquals(anotherCountry, createdCountry);

        verify(countrySpringRepository).save(countryEntity);
    }

    @Test
    void createTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> countryDatabaseRepository.create(null));

        verify(countrySpringRepository, never()).save(null);
    }

    @Test
    void updateTest_entityWithId() {
        when(countrySpringRepository.save(countryEntity))
                .thenReturn(anotherCountryEntity);

        Country updatedCountry = countryDatabaseRepository.update(country);
        assertEquals(anotherCountry, updatedCountry);

        verify(countrySpringRepository).save(countryEntity);
    }

    @Test
    void updateTest_entityWithoutId() {
        removeId();

        assertThrows(IllegalArgumentException.class, () -> countryDatabaseRepository.update(country));

        verify(countrySpringRepository, never()).save(countryEntity);
    }

    private void removeId() {
        country = country.toBuilder()
                .id(0)
                .build();
    }

    @Test
    void updateTest_nullPassed() {
        assertThrows(NullPointerException.class, () -> countryDatabaseRepository.update(null));

        verify(countrySpringRepository, never()).save(null);
    }
}