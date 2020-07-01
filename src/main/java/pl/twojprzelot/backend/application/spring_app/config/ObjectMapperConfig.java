package pl.twojprzelot.backend.application.spring_app.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
class ObjectMapperConfig {

    @Bean
    SimpleModule emptyStringAsNullModule() {
        StdDeserializer<String> emptyStringAsNullDeserializer = new StdDeserializer<>(String.class) {

            @Override
            public String deserialize(JsonParser parser, DeserializationContext context)
                    throws IOException {
                String result = StringDeserializer.instance.deserialize(parser, context);
                if (StringUtils.isEmpty(result))
                    return null;

                return result;
            }
        };

        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, emptyStringAsNullDeserializer);
        return module;
    }
}
