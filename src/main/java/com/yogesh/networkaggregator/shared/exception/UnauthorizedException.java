package com.yogesh.networkaggregator.shared.exception;

import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when authentication is required but not provided or is
 * invalid.
 * This exception represents HTTP 401 Unauthorized responses and is used when
 * a request requires authentication but none was provided, or the provided
 * credentials are invalid.
 */
public class UnauthorizedException extends BaseException {
    /**
     * The HTTP status code for unauthorized responses.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    /**
     * Constructs an UnauthorizedException with a direct error message.
     *
     * @param message the error message describing the authentication issue
     */
    public UnauthorizedException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs an UnauthorizedException with an internationalization key.
     *
     * @param messageKey the key for looking up the localized error message
     */
    public UnauthorizedException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }

    /**
     * Constructs an UnauthorizedException with an internationalization key and
     * parameters.
     *
     * @param messageKey    the key for looking up the localized error message
     * @param messageParams parameters to be substituted into the message template
     */
    public UnauthorizedException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
