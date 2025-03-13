package com.yogesh.networkaggregator.shared.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing Jackson ObjectMapper settings.
 * This class provides a configured ObjectMapper bean with specific
 * serialization
 * and deserialization settings for JSON processing throughout the application.
 */
@Configuration
public class JacksonMapperConfig {

    /**
     * Creates and configures an ObjectMapper bean with custom settings.
     * 
     * @return A configured ObjectMapper instance with:
     *         - Null value exclusion in serialization
     *         - Lenient null handling for primitives
     *         - Unknown property tolerance
     *         - Java 8 date/time support via JavaTimeModule
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Exclude null values from serialization
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // Avoid failures on deserialization when encountering null values for
        // primitives
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Set the naming strategy globally
        // objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        return objectMapper.registerModule(new JavaTimeModule());
    }
}
