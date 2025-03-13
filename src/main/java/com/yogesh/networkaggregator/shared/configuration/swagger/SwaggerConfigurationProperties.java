package com.yogesh.networkaggregator.shared.configuration.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger")
public record SwaggerConfigurationProperties(
        String version,
        String serverUrl,
        String contactEmail,
        String contactName,
        String contactUrl,
        String licenceUrl,
        String termsUrl,
        String tokenUrl
) {
}
