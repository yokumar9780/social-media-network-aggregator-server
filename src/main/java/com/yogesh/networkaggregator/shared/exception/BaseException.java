package com.yogesh.networkaggregator.shared.exception;

import com.yogesh.networkaggregator.shared.utils.I18Constants;
import com.yogesh.networkaggregator.shared.utils.LanguageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Base exception class for the application that supports internationalization
 * and HTTP status codes.
 * This class serves as the foundation for all custom exceptions in the
 * application,
 * providing support for both direct message handling and internationalized
 * messages.
 */
@Getter
@Slf4j
public class BaseException extends RuntimeException implements Serializable {
    /**
     * The HTTP status code associated with this exception.
     */
    private final HttpStatus httpStatus;

    /**
     * The internationalization constant key for message lookup.
     */
    private final I18Constants i18Constants;

    /**
     * Direct error message when not using internationalization.
     */
    private final String message;

    /**
     * Parameters to be substituted into the internationalized message template.
     */
    private final Object[] messageParams;

    /**
     * Constructs a BaseException with a direct message.
     *
     * @param httpStatus the HTTP status code for the response
     * @param message    the error message
     */
    public BaseException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        this.i18Constants = null;
        this.messageParams = null;
    }

    /**
     * Constructs a BaseException with an internationalization key.
     *
     * @param httpStatus   the HTTP status code for the response
     * @param i18Constants the internationalization constant for message lookup
     */
    public BaseException(HttpStatus httpStatus, I18Constants i18Constants) {
        this.httpStatus = httpStatus;
        this.i18Constants = i18Constants;
        this.messageParams = null;
        this.message = null;
    }

    /**
     * Constructs a BaseException with an internationalization key and message
     * parameters.
     *
     * @param httpStatus    the HTTP status code for the response
     * @param i18Constants  the internationalization constant for message lookup
     * @param messageParams parameters to be substituted into the message template
     */
    public BaseException(HttpStatus httpStatus, I18Constants i18Constants, Object... messageParams) {
        this.httpStatus = httpStatus;
        this.i18Constants = i18Constants;
        this.messageParams = messageParams;
        this.message = null;
    }

    /**
     * Retrieves the localized message for this exception.
     * If internationalization is configured, looks up the message using the
     * i18Constants key
     * and substitutes any message parameters. Otherwise, returns the direct
     * message.
     *
     * @param messageSource the message source for internationalization lookup
     * @return the localized error message
     */
    public String getMessageInLocale(MessageSource messageSource) {
        if (i18Constants != null) {
            return LanguageUtils.getLocaleMessage(messageSource, this.i18Constants.getKey(), this.messageParams);
        }
        return message;
    }
}
