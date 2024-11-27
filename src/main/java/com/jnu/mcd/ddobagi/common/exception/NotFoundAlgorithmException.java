package com.jnu.mcd.ddobagi.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundAlgorithmException extends BusinessException {

    private static final String FAIL_CODE = "4000";

    public NotFoundAlgorithmException() {
        super(FAIL_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getMessage() {
        return "암호화 알고리즘을 찾을 수 없습니다.";
    }
}
