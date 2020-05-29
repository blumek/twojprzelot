package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.Country;
import pl.twojprzelot.backend.domain.port.CountryImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
final class CountryAERepository implements CountryImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<Country> findAll() {
        AviationEdgeRequest<CountryAE> countryRequest = client.createCountryRequest()
                .build();

        return countryRequest.get().stream()
                .map(CountryAE::toCountry)
                .collect(toList());
    }

    @Override
    public Optional<Country> findByIso2Code(String iso2Code) {
        AviationEdgeRequest<CountryAE> countryRequest = client.createCountryRequest()
                .iso2Code(iso2Code)
                .build();

        return countryRequest.get()
                .stream()
                .findFirst()
                .map(CountryAE::toCountry);
    }
}
