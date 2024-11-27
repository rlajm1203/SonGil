package com.jnu.mcd.ddobagi.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidEncryptKeyException extends BusinessException {

    private static final String FAIL_CODE = "4001";

    public InvalidEncryptKeyException() {
        super(FAIL_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getMessage() {
        return "유효하지 않은 암호화 키 입니다.";
    }
}
