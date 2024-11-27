package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicatedEmailException extends BusinessException {

    private static final String FAIL_CODE = "1006";

    public DuplicatedEmailException() {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return "중복된 ID/이메일 입니다.";
    }
}
