package com.yogesh.networkaggregator.shared.exception.rest;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

/**
 * Custom error handler for REST client responses.
 * This class extends Spring's DefaultResponseErrorHandler to provide custom
 * handling
 * of HTTP 4xx and 5xx error responses when making REST client calls.
 */
public class CustomizedRestResponseErrorHandler extends DefaultResponseErrorHandler {

    /**
     * Handles error responses from REST client calls.
     * Throws specific exceptions for client (4xx) and server (5xx) errors with
     * descriptive messages. Falls back to default error handling for other cases.
     *
     * @param response the error response from the server
     * @throws IOException              if an I/O error occurs
     * @throws HttpClientErrorException for 4xx client errors
     * @throws HttpServerErrorException for 5xx server errors
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // Custom logic to handle 4xx or 5xx errors can go here
        if (response.getStatusCode().is4xxClientError()) {
            throw new HttpClientErrorException(response.getStatusCode(),
                    "Client error occurred: " + response.getStatusText());
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new HttpServerErrorException(response.getStatusCode(),
                    "Server error occurred: " + response.getStatusText());
        }
        // Call the default error handler for any other cases
        super.handleError(response);
    }
}
