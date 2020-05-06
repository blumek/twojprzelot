package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.RequiredArgsConstructor;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.port.CityRepository;

import java.util.Optional;

@RequiredArgsConstructor
class CityAERepository implements CityRepository {
    private final AviationEdgeClient client;

    @Override
    public Optional<City> findByIataCode(String iataCode) {
        CityRequest cityRequest = client.createCityRequest()
                .iataCode(iataCode)
                .create();

        return cityRequest.get().stream()
                .findFirst()
                .map(CityAE::toCity);
    }
}
