package com.jnu.mcd.ddobagi.common.exception;

import org.springframework.http.HttpStatus;

public class SMSPostFailedException extends BusinessException {

    private static final String FAIL_CODE = "4002";

    public SMSPostFailedException() {
        super(FAIL_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getMessage() {
        return "메세지 전송에 실패하였습니다.";
    }
}
