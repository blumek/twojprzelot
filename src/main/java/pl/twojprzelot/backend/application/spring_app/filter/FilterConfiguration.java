package pl.twojprzelot.backend.application.spring_app.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FilterConfiguration {
    @Bean
    AuthenticationFilter authenticationFilter(@Value("${application.key.header}") String applicationKeyHeaderName,
                                              @Value("${applicationKey}") String applicationKey,
                                              FilterExceptionHandler filterExceptionHandler) {
        return new AuthenticationFilter(applicationKeyHeaderName, applicationKey, filterExceptionHandler);
    }
}
