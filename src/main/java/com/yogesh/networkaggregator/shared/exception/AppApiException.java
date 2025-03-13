package com.yogesh.networkaggregator.shared.exception;


import org.springframework.http.HttpStatus;

public class AppApiException extends BaseException {

    public AppApiException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }

}
