package com.jnu.mcd.ddobagi.domains.member.application.exception;

import com.jnu.mcd.ddobagi.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CertNumberExpiredException extends BusinessException {

    private static final String FAIL_CODE = "6003";
    private final String certNumber;

    public CertNumberExpiredException(String certNumber) {
        super(FAIL_CODE, HttpStatus.BAD_REQUEST);
        this.certNumber = certNumber;
    }

    @Override
    public String getMessage() {
        return String.format("인증번호가 만료되었습니다. : %s", certNumber);
    }
}
