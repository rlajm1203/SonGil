package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class SignUpIdPasswordPolicyViolationException extends BusinessException {

    private static final String FAIL_CODE = "1003";

    public SignUpIdPasswordPolicyViolationException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "입력하신 ID 또는 패스워드가 정책에 맞지 않습니다.";
    }
}
