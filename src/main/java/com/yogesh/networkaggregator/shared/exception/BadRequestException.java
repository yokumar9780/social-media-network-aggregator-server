package com.yogesh.networkaggregator.shared.exception;


import com.yogesh.networkaggregator.shared.utils.I18Constants;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(HTTP_STATUS, message);
    }

    public BadRequestException(I18Constants messageKey) {
        super(HTTP_STATUS, messageKey);
    }


    public BadRequestException(I18Constants messageKey, Object... messageParams) {
        super(HTTP_STATUS, messageKey, messageParams);
    }
}
