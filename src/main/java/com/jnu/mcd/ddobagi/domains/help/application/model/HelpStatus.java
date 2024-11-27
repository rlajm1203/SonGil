package com.jnu.mcd.ddobagi.domains.help.application.model;

import java.util.Arrays;

public enum HelpStatus {
    
    NON_MATCH("nonMatch"),
    MATHING("matching"),
    SOLVED("solved");

    String status;

    HelpStatus(String status) {
        this.status = status;
    }

    public static HelpStatus fromString(String status) {
        return Arrays.stream(HelpStatus.values())
                .filter(helpStatus -> helpStatus.status.equals(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown help status: " + status));
    }

    @Override
    public String toString() {
        return status;
    }
    
}
