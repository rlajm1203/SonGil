package com.jnu.mcd.ddobagi.domains.member.application.exception;


import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class IsNotFormatPhoneNumberException extends BusinessException {

    private static final String FAIL_CODE = "";

    public IsNotFormatPhoneNumberException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "입력하신 전화번호가 형식에 맞지 않습니다.";
    }
}
