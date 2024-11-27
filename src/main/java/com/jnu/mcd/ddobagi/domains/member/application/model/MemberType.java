package com.jnu.mcd.ddobagi.domains.member.application.model;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum MemberType {

    ELDERLY("elderly"),
    HELPER("helper");

    MemberType(String type) {
        this.type = type;
    }

    private String type;
    
    public static MemberType findMemberType(String type) {
        return Arrays.stream(MemberType.values())
                .filter(memberType -> memberType.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Member Type을 찾을 수 없습니다."));
    }

}
