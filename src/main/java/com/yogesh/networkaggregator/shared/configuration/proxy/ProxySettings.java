package com.yogesh.networkaggregator.shared.configuration.proxy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Configuration properties for HTTP proxy settings.
 * This record holds proxy server configuration details loaded from application
 * properties with the 'proxy' prefix.
 */
@ConfigurationProperties(prefix = "proxy")
public record ProxySettings(
                /**
                 * The hostname or IP address of the proxy server.
                 */
                String host,

                /**
                 * The port number of the proxy server.
                 * Defaults to 80 if not specified.
                 */
                @DefaultValue("80") int port,

                /**
                 * The username for proxy authentication, if required.
                 */
                String username,

                /**
                 * The password for proxy authentication, if required.
                 */
                String password) {
}
