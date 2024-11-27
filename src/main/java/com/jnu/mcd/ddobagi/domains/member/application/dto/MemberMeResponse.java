package com.jnu.mcd.ddobagi.domains.member.application.dto;

import com.jnu.mcd.ddobagi.domains.member.persistence.Member;

public record MemberMeResponse(
        Long memberId,
        String memberName
){

    public static MemberMeResponse fromMember(Member member) {
        return new MemberMeResponse(member.getMemberId(), member.getName());
    }

}
