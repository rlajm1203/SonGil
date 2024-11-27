package com.jnu.mcd.ddobagi.domains.member.application.exception;


import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundPhoneNumberException extends BusinessException {

    private static final String FAIL_CODE = "6002";

    public NotFoundPhoneNumberException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "입력하신 전화번호를 찾을 수 없습니다.";
    }
}
