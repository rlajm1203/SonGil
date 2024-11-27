package com.jnu.mcd.ddobagi.domains.member.application.exception;


import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotCertifiedPhoneNumber extends BusinessException {

    private static final String FAIL_CODE = "6005";

    public NotCertifiedPhoneNumber() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "인증되지 않은 전화번호 입니다.";
    }
}
