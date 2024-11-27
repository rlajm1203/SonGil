package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BusinessException {

    private static final String FAIL_CODE = "1006";

    public InvalidTokenException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "유효하지 않은 토큰입니다.";
    }
}
