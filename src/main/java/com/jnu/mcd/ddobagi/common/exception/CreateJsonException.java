package com.jnu.mcd.ddobagi.common.exception;

import org.springframework.http.HttpStatus;

public class CreateJsonException extends BusinessException {

    private static final String FAIL_CODE = "10000";

    public CreateJsonException() {
        super(FAIL_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getMessage() {
        return "JSON 생성 중 에러 발생";
    }
}
