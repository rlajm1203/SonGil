package com.jnu.mcd.ddobagi.domains.member.application.exception;


import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundKeyException extends BusinessException {

    private static final String FAIL_CODE = "1007";

    public NotFoundKeyException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "입력하신 인증번호가 잘못되었습니다.";
    }
}
