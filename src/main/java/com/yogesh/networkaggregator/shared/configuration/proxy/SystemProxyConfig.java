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

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class SystemProxyConfig implements InitializingBean {
    private final ProxySettings proxy;
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Override
    public void afterPropertiesSet() {
        System.setProperty("http.proxyHost", proxy.host());
        System.setProperty("http.proxyPort", Integer.toString(proxy.port()));

        System.setProperty("https.proxyHost", proxy.host());
        System.setProperty("https.proxyPort", Integer.toString(proxy.port()));
    }

    @Primary
    @Bean
    public RestClient restClient() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.host(), proxy.port())));

        Authenticator.setDefault(new Authenticator() {
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

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.host(), proxy.port())));
        // Set proxy authentication if username and password are provided

        Authenticator.setDefault(new Authenticator() {
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
        // Set a custom error handler
        restTemplate.setErrorHandler(new CustomizedRestResponseErrorHandler());
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 5 seconds
        factory.setReadTimeout(10000);   // 10 seconds

        return restTemplate;
    }


}
