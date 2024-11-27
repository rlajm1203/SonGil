package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenExpiredException extends BusinessException {

    public static final String FAIL_CODE = "1000";

    public TokenExpiredException() {
        super(FAIL_CODE, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public String getMessage() {
        return "토큰이 만료되었습니다.";
    }
}
