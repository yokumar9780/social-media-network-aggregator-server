package com.yogesh.networkaggregator.shared.exception;


import com.yogesh.networkaggregator.shared.utils.I18Constants;
import com.yogesh.networkaggregator.shared.utils.LanguageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Slf4j
public class BaseException extends RuntimeException implements Serializable {
    private final HttpStatus httpStatus;
    private final I18Constants i18Constants;
    // Error message associated with the exception
    private final String message;
    // Parameters for substituting into the internationalized message
    private final Object[] messageParams;

    public BaseException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        this.i18Constants = null;
        this.messageParams = null;
    }


    public BaseException(HttpStatus httpStatus, I18Constants i18Constants) {
        this.httpStatus = httpStatus;
        this.i18Constants = i18Constants;
        this.messageParams = null;
        this.message = null;
    }


    public BaseException(HttpStatus httpStatus, I18Constants i18Constants, Object... messageParams) {
        this.httpStatus = httpStatus;
        this.i18Constants = i18Constants;
        this.messageParams = messageParams;
        this.message = null;
    }

    public String getMessageInLocale(MessageSource messageSource) {
        if (i18Constants != null) {
            return LanguageUtils.getLocaleMessage(messageSource, this.i18Constants.getKey(), this.messageParams);
        }
        return message;
    }
}
