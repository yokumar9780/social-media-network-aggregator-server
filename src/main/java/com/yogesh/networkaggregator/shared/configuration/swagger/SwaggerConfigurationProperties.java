package com.yogesh.networkaggregator.shared.configuration.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Swagger/OpenAPI documentation.
 * This record holds various configuration settings for customizing the
 * Swagger/OpenAPI
 * documentation interface, loaded from application properties with the
 * 'swagger' prefix.
 */
@ConfigurationProperties(prefix = "swagger")
public record SwaggerConfigurationProperties(
                /**
                 * The API version number.
                 */
                String version,

                /**
                 * The base URL of the API server.
                 */
                String serverUrl,

                /**
                 * The contact email address for API support.
                 */
                String contactEmail,

                /**
                 * The name of the contact person or team for API support.
                 */
                String contactName,

                /**
                 * The URL to the contact page or support portal.
                 */
                String contactUrl,

                /**
                 * The URL to the API license documentation.
                 */
                String licenceUrl,

                /**
                 * The URL to the terms of service documentation.
                 */
                String termsUrl,

                /**
                 * The URL for obtaining authentication tokens.
                 */
                String tokenUrl) {
}
