package com.yogesh.networkaggregator.shared.configuration.audit;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableMongoAuditing
public class AuditConfiguration {

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
