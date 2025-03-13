package com.yogesh.networkaggregator.shared.exception.rest;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

public class CustomizedRestResponseErrorHandler extends DefaultResponseErrorHandler {


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // Custom logic to handle 4xx or 5xx errors can go here
        if (response.getStatusCode().is4xxClientError()) {
            throw new HttpClientErrorException(response.getStatusCode(), "Client error occurred: " + response.getStatusText());
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new HttpServerErrorException(response.getStatusCode(), "Server error occurred: " + response.getStatusText());
        }
        // Call the default error handler for any other cases
        super.handleError(response);
    }
}

