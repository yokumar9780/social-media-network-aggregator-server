package com.yogesh.networkaggregator.shared.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for OpenAPI/Swagger documentation.
 * This class sets up the OpenAPI specification for the Network Aggregator API,
 * including security schemes, server information, API information, and
 * documentation grouping.
 */
@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
        private final SwaggerConfigurationProperties properties;

        /**
         * Creates a grouped OpenAPI configuration for the Network Aggregator API.
         * This configuration groups all API endpoints under the "NetworkAggregator"
         * group
         * and scans the specified package for API documentation.
         *
         * @return GroupedOpenApi configuration for the Network Aggregator
         */
        @Bean
        public GroupedOpenApi customGroupedOpenApi() {
                return GroupedOpenApi.builder().group("NetworkAggregator")
                                .packagesToScan("com.yogesh.networkaggregator")
                                .build();
        }

        /**
         * Creates the main OpenAPI configuration for the Network Aggregator API.
         * This configuration includes:
         * - OAuth2 security configuration
         * - Server information
         * - Contact information
         * - License details
         * - API version and description
         *
         * @return OpenAPI configuration with complete API documentation setup
         */
        @Bean
        public OpenAPI customOpenAPI() {
                OAuthFlow oAuthFlow = new OAuthFlow()
                                .tokenUrl(properties.tokenUrl());
                SecurityScheme securityScheme = new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows().clientCredentials(oAuthFlow))
                                .name("OAuth2");

                Server server = new Server()
                                .description("Network Aggregator API service server")
                                .url(properties.serverUrl());

                Contact contact = new Contact();
                contact.setEmail(properties.contactEmail());
                contact.setName(properties.contactName());
                contact.setUrl(properties.contactUrl());
                License mitLicense = new License().name("Network Aggregator License")
                                .url(properties.licenceUrl());

                var info = new Info()
                                .title("Network Aggregator Logivity API")
                                .contact(contact)
                                .description("Network Aggregator API").termsOfService(properties.termsUrl())
                                .license(mitLicense)
                                .version(properties.version());

                var components = new Components()
                                .addSecuritySchemes("OAuth2", securityScheme);

                return new OpenAPI()
                                .servers(List.of(server))
                                .components(components)
                                .info(info)
                                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                                                .addList("OAuth2"));
        }
}