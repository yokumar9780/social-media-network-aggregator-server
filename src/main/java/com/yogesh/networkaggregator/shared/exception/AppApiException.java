package com.yogesh.networkaggregator.shared.exception;

import org.springframework.http.HttpStatus;

/**
 * General purpose API exception for application-level errors.
 * This exception is used to represent various API-related errors that occur
 * during application execution but don't fall into more specific exception
 * categories.
 */
public class AppApiException extends BaseException {

    /**
     * Constructs a new AppApiException with a custom message and HTTP status.
     *
     * @param message    the error message describing the cause
     * @param httpStatus the HTTP status code to be returned in the response
     */
    public AppApiException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }

}
