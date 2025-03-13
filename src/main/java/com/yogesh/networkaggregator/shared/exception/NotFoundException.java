package com.yogesh.networkaggregator.shared.exception;

import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested resource is not found.
 * This exception represents HTTP 404 Not Found responses and is used when
 * attempting to access or manipulate a resource that doesn't exist.
 */
public class NotFoundException extends BaseException {
    /**
     * The HTTP status code for not found responses.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    /**
     * Constructs a NotFoundException with a direct error message.
     *
     * @param message the error message describing what resource was not found
     */
    public NotFoundException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a NotFoundException with an internationalization key.
     *
     * @param messageKey the key for looking up the localized error message
     */
    public NotFoundException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }

    /**
     * Constructs a NotFoundException with an internationalization key and
     * parameters.
     *
     * @param messageKey    the key for looking up the localized error message
     * @param messageParams parameters to be substituted into the message template
     */
    public NotFoundException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
