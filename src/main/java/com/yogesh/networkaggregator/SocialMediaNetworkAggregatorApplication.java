package com.yogesh.networkaggregator;

import com.yogesh.networkaggregator.shared.configuration.PaginationConfig;
import com.yogesh.networkaggregator.shared.configuration.proxy.ProxySettings;
import com.yogesh.networkaggregator.shared.configuration.security.CorsAllowedConfig;
import com.yogesh.networkaggregator.shared.configuration.swagger.SwaggerConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.modulith.Modulithic;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableScheduling
@Modulithic
@EnableConfigurationProperties({ProxySettings.class, PaginationConfig.class,
        CorsAllowedConfig.class, SwaggerConfigurationProperties.class})

public class SocialMediaNetworkAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaNetworkAggregatorApplication.class, args);
    }

}
