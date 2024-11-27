package com.jnu.mcd.ddobagi.common.presentation.response;

public enum MessageCode {
    CREATE("201", "생성 성공"),
    GET("200", "조회 성공"),
    UPDATE("200", "수정 성공"),
    DELETE("200", "삭제 성공"),
    LOGIN("200", "로그인 성공"),
    LOGOUT("200", "로그아웃 성공"),
    PHONE_AUTH("200", "인증 성공"),
    EMAIL_SUCCESS("200", "사용가능한 이메일입니다.");
    private final String code;
    private final String message;

    MessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
