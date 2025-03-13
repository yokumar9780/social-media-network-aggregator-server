package com.yogesh.networkaggregator.shared.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pagination")
public record PaginationConfig(
        int defaultPageSize,
        int defaultPageNumber
) {
}
