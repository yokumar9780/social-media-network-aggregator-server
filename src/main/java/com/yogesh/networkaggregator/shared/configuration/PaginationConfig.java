package com.yogesh.networkaggregator.shared.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration record for pagination settings.
 * This record holds the default pagination parameters loaded from application
 * properties
 * with the 'pagination' prefix.
 */
@ConfigurationProperties(prefix = "pagination")
public record PaginationConfig(
                /**
                 * The default number of items to display per page.
                 */
                int defaultPageSize,

                /**
                 * The default starting page number (zero-based).
                 */
                int defaultPageNumber) {
}
