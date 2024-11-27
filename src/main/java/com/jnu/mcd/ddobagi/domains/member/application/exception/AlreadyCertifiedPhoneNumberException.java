package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AlreadyCertifiedPhoneNumberException extends BusinessException {

    private static final String FAIL_CODE = "6006";

    public AlreadyCertifiedPhoneNumberException(){
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage(){ return "이미 인증된 전화번호 입니다."; }

}
