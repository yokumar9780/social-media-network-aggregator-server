package com.yogesh.networkaggregator.shared.configuration.proxy;

import com.yogesh.networkaggregator.shared.exception.rest.CustomizedRestResponseErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * Configuration class for system-wide HTTP proxy settings.
 * This class configures proxy settings for the application and provides
 * proxy-aware
 * RestTemplate and RestClient beans. It is only active in the 'dev' profile.
 */
@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class SystemProxyConfig implements InitializingBean {
    private final ProxySettings proxy;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    /**
     * Initializes system-wide proxy properties after bean construction.
     * Sets HTTP and HTTPS proxy host and port system properties.
     */
    @Override
    public void afterPropertiesSet() {
        System.setProperty("http.proxyHost", proxy.host());
        System.setProperty("http.proxyPort", Integer.toString(proxy.port()));

        System.setProperty("https.proxyHost", proxy.host());
        System.setProperty("https.proxyPort", Integer.toString(proxy.port()));
    }

    /**
     * Creates a proxy-aware RestClient bean.
     * This RestClient is configured with proxy settings and basic authentication
     * if credentials are provided.
     *
     * @return configured RestClient instance with proxy support
     */
    @Primary
    @Bean
    public RestClient restClient() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.host(), proxy.port())));

        Authenticator.setDefault(new Authenticator() {
            /**
             * Provides proxy authentication credentials when requested.
             *
             * @return PasswordAuthentication for proxy authentication
             */
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestorType() == RequestorType.PROXY) {
                    return new PasswordAuthentication(proxy.username(), proxy.password().toCharArray());
                } else {
                    return super.getPasswordAuthentication();
                }
            }
        });
        return RestClient.builder()
                .defaultHeaders(headers -> headers.set("Accept", "application/json"))
                .requestFactory(requestFactory)
                .build();
    }

    /**
     * Creates a proxy-aware RestTemplate bean.
     * This RestTemplate is configured with proxy settings, basic authentication,
     * custom error handling, and connection timeouts.
     *
     * @return configured RestTemplate instance with proxy support
     */
    @Primary
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.host(), proxy.port())));

        Authenticator.setDefault(new Authenticator() {
            /**
             * Provides proxy authentication credentials when requested.
             *
             * @return PasswordAuthentication for proxy authentication
             */
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestorType() == RequestorType.PROXY) {
                    return new PasswordAuthentication(proxy.username(), proxy.password().toCharArray());
                } else {
                    return super.getPasswordAuthentication();
                }
            }
        });

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(new CustomizedRestResponseErrorHandler());
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 5 seconds
        factory.setReadTimeout(10000); // 10 seconds

        return restTemplate;
    }
}
