package com.yogesh.networkaggregator.shared.exception;

import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when the client sends an invalid request.
 * This exception represents HTTP 400 Bad Request responses and is used when
 * request validation fails or when the request contains invalid parameters.
 */
public class BadRequestException extends BaseException {
    /**
     * The HTTP status code for bad request responses.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    /**
     * Constructs a BadRequestException with a direct error message.
     *
     * @param message the error message describing the validation failure
     */
    public BadRequestException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a BadRequestException with an internationalization key.
     *
     * @param messageKey the key for looking up the localized error message
     */
    public BadRequestException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }

    /**
     * Constructs a BadRequestException with an internationalization key and
     * parameters.
     *
     * @param messageKey    the key for looking up the localized error message
     * @param messageParams parameters to be substituted into the message template
     */
    public BadRequestException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
