package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.port.CountryRepository;

import java.util.Optional;

@RequiredArgsConstructor
class CountryAERepository implements CountryRepository {
    private final AviationEdgeClient client;

    @Override
    public Optional<Country> findByIso2Code(String iso2Code) {
        CountryRequest countryRequest = client.createCountryRequest()
                .iso2Code(iso2Code)
                .create();

        return countryRequest.get().stream()
                .findFirst()
                .map(CountryAE::toCountry);
    }
}
