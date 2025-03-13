package com.yogesh.networkaggregator.shared.exception;

import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a user attempts to access a resource without proper
 * permissions.
 * This exception represents HTTP 403 Forbidden responses and is used when
 * an authenticated user lacks the necessary permissions to perform an action.
 */
public class PermissionDeniedException extends BaseException {
    /**
     * The HTTP status code for forbidden responses.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

    /**
     * Constructs a PermissionDeniedException with a direct error message.
     *
     * @param message the error message describing the permission issue
     */
    public PermissionDeniedException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a PermissionDeniedException with an internationalization key.
     *
     * @param messageKey the key for looking up the localized error message
     */
    public PermissionDeniedException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }

    /**
     * Constructs a PermissionDeniedException with an internationalization key and
     * parameters.
     *
     * @param messageKey    the key for looking up the localized error message
     * @param messageParams parameters to be substituted into the message template
     */
    public PermissionDeniedException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
