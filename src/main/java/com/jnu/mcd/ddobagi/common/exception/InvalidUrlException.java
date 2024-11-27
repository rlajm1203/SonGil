package com.jnu.mcd.ddobagi.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidUrlException extends BusinessException {

    private static final String FAIL_CODE = "10001";

    public InvalidUrlException() {
        super(FAIL_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getMessage() {
        return "URL 주소가 잘못되었습니다.";
    }
}
