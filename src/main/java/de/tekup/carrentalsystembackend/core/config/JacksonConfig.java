package de.tekup.carrentalsystembackend.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;

public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Configure to prevent infinite recursion (limit serialization depth)
        mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        return mapper;
    }
}