package com.jnu.mcd.ddobagi.domains.help.application.model;

import java.util.Arrays;

public enum HelpStatus {
    
    NON_MATCH("nonMatch", "매칭 대기 중"),
    MATHING("matching", "매칭 완료"),
    SOLVED("solved", "해결 완료");

    String status;
    String koreanStatus;

    HelpStatus(String status, String koreanStatus) {
        this.status = status;
        this.koreanStatus = koreanStatus;
    }

    public static HelpStatus fromString(String status) {
        return Arrays.stream(HelpStatus.values())
                .filter(helpStatus -> helpStatus.status.equals(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown help status: " + status));
    }

    @Override
    public String toString() {
        return koreanStatus;
    }
    
}
