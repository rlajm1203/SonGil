package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PhoneAuthenticationFailedException extends BusinessException {

    private static final String FAIL_CODE = "6004";

    public PhoneAuthenticationFailedException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "휴대폰 인증에 실패하였습니다.";
    }
}
