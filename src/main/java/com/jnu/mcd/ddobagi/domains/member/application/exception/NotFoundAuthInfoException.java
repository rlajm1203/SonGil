package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundAuthInfoException extends BusinessException {

    private static final String FAIL_CODE = "1004";

    private final String loginId;

    public NotFoundAuthInfoException(String loginId) {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
        this.loginId = loginId;
    }

    @Override
    public String getMessage() {
        return "계정 정보가 존재하지 않습니다. (ID) : " + loginId;
    }
}
