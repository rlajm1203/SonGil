package com.jnu.mcd.ddobagi.domains.help.application.model;

import java.util.Arrays;

public enum HelpType {

    ONCE("once"),
    REGULRAY("regulary");

    private String type;

    HelpType(String type) {
        this.type = type;
    }

    public static HelpType fromString(String type) {
        return Arrays.stream(HelpType.values())
                .filter(helpType -> helpType.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid help type: " + type));
    }

    @Override
    public String toString() {
        return type;
    }

}
