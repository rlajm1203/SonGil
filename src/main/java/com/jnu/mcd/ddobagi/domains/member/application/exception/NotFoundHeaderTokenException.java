package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundHeaderTokenException extends BusinessException {

    private static final String FAIL_CODE = "1005";

    public NotFoundHeaderTokenException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "토큰이 존재하지 않습니다.";
    }
}
