package com.yogesh.networkaggregator.shared.exception;

import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when attempting to create a resource that already exists.
 * This exception represents HTTP 409 Conflict responses and is used when
 * a create operation would result in a duplicate resource or violate uniqueness
 * constraints.
 */
public class ResourceExistsException extends BaseException {
    /**
     * The HTTP status code for conflict responses.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

    /**
     * Constructs a ResourceExistsException with a direct error message.
     *
     * @param message the error message describing the conflict
     */
    public ResourceExistsException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a ResourceExistsException with an internationalization key.
     *
     * @param messageKey the key for looking up the localized error message
     */
    public ResourceExistsException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }

    /**
     * Constructs a ResourceExistsException with an internationalization key and
     * parameters.
     *
     * @param messageKey    the key for looking up the localized error message
     * @param messageParams parameters to be substituted into the message template
     */
    public ResourceExistsException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
