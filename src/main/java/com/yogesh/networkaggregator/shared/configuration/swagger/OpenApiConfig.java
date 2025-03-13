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

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
    private final SwaggerConfigurationProperties properties;

    @Bean
    public GroupedOpenApi customGroupedOpenApi() {
        return GroupedOpenApi.builder().group("NetworkAggregator")
                .packagesToScan("com.yogesh.networkaggregator")
                .build();
    }

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
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("OAuth2"));
    }
}