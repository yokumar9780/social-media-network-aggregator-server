package com.yogesh.networkaggregator.shared.configuration.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

/**
 * Configuration class for MongoDB auditing support.
 * This class enables automatic auditing of MongoDB documents by capturing
 * the currently authenticated user for creation and modification tracking.
 * It integrates with Spring Security to obtain the current user's information.
 */
@Configuration
@EnableMongoAuditing
public class AuditConfiguration {

    /**
     * Creates an AuditorAware bean that provides the current user's identity for
     * auditing.
     * This bean is used by Spring Data MongoDB to automatically populate audit
     * fields
     * (created by, modified by) in documents. It retrieves the username from the
     * current
     * Spring Security context.
     *
     * @return an AuditorAware implementation that provides the current user's
     *         username
     */
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> {
            var securityContext = SecurityContextHolder
                    .getContext();
            if (Objects.nonNull(securityContext.getAuthentication())) {
                return Optional.of(securityContext.getAuthentication().getName());
            }
            return Optional.empty();
        };
    }
}
