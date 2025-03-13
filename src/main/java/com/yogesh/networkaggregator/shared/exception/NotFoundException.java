package com.yogesh.networkaggregator.shared.exception;


import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;


public class NotFoundException extends BaseException {

    // HTTP status code associated with a Bad Request
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(HTTP_STATUS, message);
    }


    public NotFoundException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }


    public NotFoundException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }

}
