package pl.twojprzelot.backend.adapter.repository.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.twojprzelot.backend.domain.entity.Country;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDatabaseRepositoryTest {
    private static final String ISO_2_CODE = "ISO_2_CODE";
    private static final int ID = 1;
    private static final String NAME = "NAME";

    @InjectMocks
    private CountryDatabaseRepository countryDatabaseRepository;
    @Mock
    private CountrySpringRepository countrySpringRepository;

    @Test
    void findByIso2CodeTest_countryWithGivenIso2CodeExists() {
        when(countrySpringRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.empty());

        Optional<Country> foundCountry = countryDatabaseRepository.findByIso2Code(ISO_2_CODE);
        assertEquals(Optional.empty(), foundCountry);
    }

    @Test
    void findByIso2CodeTest_countryWithGivenIso2CodeNotExists() {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(ID);
        countryEntity.setIso2Code(ISO_2_CODE);
        countryEntity.setName(NAME);

        when(countrySpringRepository.findByIso2Code(ISO_2_CODE))
                .thenReturn(Optional.of(countryEntity));

        Country expectedCountry = Country.builder()
                .id(ID)
                .iso2Code(ISO_2_CODE)
                .name(NAME)
                .build();

        Optional<Country> foundCountry = countryDatabaseRepository.findByIso2Code(ISO_2_CODE);
        assertEquals(Optional.of(expectedCountry), foundCountry);
    }
}