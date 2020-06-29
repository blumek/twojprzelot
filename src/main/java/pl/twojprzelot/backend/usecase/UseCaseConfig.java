package pl.twojprzelot.backend.usecase;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.twojprzelot.backend.domain.entity.Currency;
import pl.twojprzelot.backend.domain.port.*;

import java.util.List;
import java.util.Optional;

@Configuration
class UseCaseConfig {

    @Bean
    ImportFlight importFlight(@Qualifier("flightAERepository") FlightImmutableRepository flightSourceRepository,
                              @Qualifier("flightDatabaseRepository") FlightMutableRepository flightTargetRepository) {
        return new ImportFlight(flightSourceRepository, flightTargetRepository);
    }

    @Bean
    ImportCountry importCountry(@Qualifier("countryAERepository") CountryImmutableRepository countrySourceRepository,
                                @Qualifier("countryDatabaseRepository") CountryMutableRepository countryTargetRepository,
                                @Qualifier("currencyDatabaseRepository") CurrencyImmutableRepository currencyRepository) {
        return new ImportCountry(countrySourceRepository, countryTargetRepository, currencyRepository);
    }

    @Bean
    ImportCity importCity(@Qualifier("cityAERepository") CityImmutableRepository citySourceRepository,
                          @Qualifier("cityDatabaseRepository") CityMutableRepository cityTargetRepository,
                          @Qualifier("countryDatabaseRepository") CountryImmutableRepository countryRepository) {
        return new ImportCity(citySourceRepository, cityTargetRepository, countryRepository);
    }

    @Bean
    ImportAirline importAirline(@Qualifier("airlineAERepository") AirlineImmutableRepository airlineSourceRepository,
                                @Qualifier("airlineDatabaseRepository") AirlineMutableRepository airlineTargetRepository) {
        return new ImportAirline(airlineSourceRepository, airlineTargetRepository);
    }

    @Bean
    ImportCurrency importCurrency(@Qualifier("currencySourceRepository") CurrencyImmutableRepository currencySourceRepository,
                                  @Qualifier("currencyDatabaseRepository") CurrencyMutableRepository currencyTargetRepository) {
        return new ImportCurrency(currencySourceRepository, currencyTargetRepository);
    }

    @Bean
    CurrencyImmutableRepository currencySourceRepository() {
        return new CurrencyImmutableRepository() {
            @Override
            public List<Currency> findAll() {
                return Lists.newArrayList();
            }

            @Override
            public Optional<Currency> findByCode(String code) {
                return Optional.empty();
            }

            @Override
            public Optional<Currency> findByIsoNumber(int isoNumber) {
                return Optional.empty();
            }
        };
    }
}
