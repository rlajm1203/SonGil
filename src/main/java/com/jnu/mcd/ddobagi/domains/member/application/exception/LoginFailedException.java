package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class LoginFailedException extends BusinessException {

    private static final String FAIL_CODE = "1002";

    public LoginFailedException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "ID 또는 패스워드가 일치하지 않습니다.";
    }
}
