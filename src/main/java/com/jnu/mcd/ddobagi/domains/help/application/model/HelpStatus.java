package com.jnu.mcd.ddobagi.domains.help.application.model;

public enum HelpStatus {
    
    NON_MATCH("nonMatch"),
    MATHING("matching"),
    SOLVED("solved");

    String status;

    HelpStatus(String status) {
        this.status = status;
    }

    public static HelpStatus fromString(String status) {
        return HelpStatus.valueOf(status);
    }

    @Override
    public String toString() {
        return status;
    }
    
}
