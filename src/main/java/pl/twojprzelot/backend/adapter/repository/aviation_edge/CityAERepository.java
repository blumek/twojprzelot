package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.port.CityImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
final class CityAERepository implements CityImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<City> findAll() {
        CityRequest cityRequest = client.createCityRequest()
                .create();

        return cityRequest.get().stream()
                .map(CityAE::toCity)
                .collect(toList());
    }

    @Override
    public Optional<City> findByIataCode(@NonNull String iataCode) {
        CityRequest cityRequest = client.createCityRequest()
                .iataCode(iataCode)
                .create();

        return cityRequest.get().stream()
                .findFirst()
                .map(CityAE::toCity);
    }
}
