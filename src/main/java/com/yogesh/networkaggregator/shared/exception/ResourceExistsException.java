package com.yogesh.networkaggregator.shared.exception;


import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;


public class ResourceExistsException extends BaseException {

    // HTTP status code associated with a Bad Request
    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;


    public ResourceExistsException(String message) {
        super(HTTP_STATUS, message);
    }


    public ResourceExistsException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }


    public ResourceExistsException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
