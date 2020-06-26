package pl.twojprzelot.backend.adapter.repository.aviation_edge;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.twojprzelot.backend.domain.entity.City;
import pl.twojprzelot.backend.domain.port.CityImmutableRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
final class CityAERepository implements CityImmutableRepository {
    private final AviationEdgeClient client;

    @Override
    public List<City> findAll() {
        AviationEdgeRequest<CityAE> cityRequest = client.createCityRequest()
                .build();

        return cityRequest.get()
                .stream()
                .map(CityAE::toCity)
                .collect(toList());
    }

    @Override
    public Optional<City> findByIataCode(@NonNull String iataCode) {
        AviationEdgeRequest<CityAE> cityRequest = client.createCityRequest()
                .iataCode(iataCode)
                .build();

        return cityRequest.get()
                .stream()
                .findFirst()
                .map(CityAE::toCity);
    }
}
