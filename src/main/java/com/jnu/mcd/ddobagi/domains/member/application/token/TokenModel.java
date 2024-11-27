package com.jnu.mcd.ddobagi.domains.member.application.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TokenModel {

    private String accessToken;
    private Long accessExpiredTime;
    private String refreshToken;
    private Long refreshExpiredTime;
}
