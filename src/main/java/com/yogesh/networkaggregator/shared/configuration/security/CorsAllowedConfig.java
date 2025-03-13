package com.yogesh.networkaggregator.shared.configuration.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cors.allowed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorsAllowedConfig {
    private String[] origins;
    private String[] methods;
    private String[] headers;
}
