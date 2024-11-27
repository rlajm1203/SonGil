package com.jnu.mcd.ddobagi.domains.member.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
public class MemberSignUpRequest {

    private String id;
    private String password;
    private String email;
    private String name;
}
