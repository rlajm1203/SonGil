package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenParsingException extends BusinessException {

    private static final String FAIL_CODE = "1001";

    public TokenParsingException() {
        super(FAIL_CODE, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public String getMessage() {
        return "토큰 파싱 중 에러가 발생하였습니다.";
    }
}
