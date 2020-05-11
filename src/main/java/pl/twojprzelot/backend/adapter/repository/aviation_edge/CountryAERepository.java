package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.port.ImmutableCountryRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
class CountryAERepository implements ImmutableCountryRepository {
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

    @Override
    public List<Country> findAll() {
        CountryRequest countryRequest = client.createCountryRequest()
                .create();

        return countryRequest.get().stream()
                .map(CountryAE::toCountry)
                .collect(toList());
    }
}
