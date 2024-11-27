package com.jnu.mcd.ddobagi.domains.help.application.model;

public enum HelpType {

    ONCE("once"),
    REGULRAY("regulary");

    private String type;

    HelpType(String type) {
        this.type = type;
    }

    public static HelpType fromString(String type) {
        return HelpType.valueOf(type.toLowerCase());
    }

}
