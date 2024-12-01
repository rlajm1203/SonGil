package com.jnu.mcd.ddobagi.domains.help.application.model;

import java.util.Arrays;

public enum HelpType {

    ONCE("once", "단기"),
    REGULRAY("regulary", "장기");

    private String type;
    private String koType;

    HelpType(String type, String koType) {
        this.type = type;
        this.koType = koType;
    }

    public static HelpType fromString(String type) {
        return Arrays.stream(HelpType.values())
                .filter(helpType -> helpType.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid help type: " + type));
    }

    @Override
    public String toString() {
        return koType;
    }

}
