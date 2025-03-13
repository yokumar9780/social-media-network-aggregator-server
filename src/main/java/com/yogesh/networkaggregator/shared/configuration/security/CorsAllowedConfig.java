package com.yogesh.networkaggregator.shared.configuration.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Cross-Origin Resource Sharing (CORS) settings.
 * This class defines the allowed origins, HTTP methods, and headers for CORS
 * requests,
 * loaded from application properties with the 'cors.allowed' prefix.
 */
@ConfigurationProperties(prefix = "cors.allowed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorsAllowedConfig {
    /**
     * Array of allowed origin domains for CORS requests.
     */
    private String[] origins;

    /**
     * Array of allowed HTTP methods (e.g., GET, POST, PUT, DELETE).
     */
    private String[] methods;

    /**
     * Array of allowed HTTP headers in CORS requests.
     */
    private String[] headers;
}
