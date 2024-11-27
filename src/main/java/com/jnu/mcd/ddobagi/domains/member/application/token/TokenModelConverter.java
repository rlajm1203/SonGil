package com.jnu.mcd.ddobagi.domains.member.application.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenModelConverter {

    public TokenModel from(
            String accessToken,
            Long accessExpirationTime,
            String refreshToken,
            Long refreshExpirationTime) {
        return TokenModel.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredTime(accessExpirationTime)
                .refreshExpiredTime(refreshExpirationTime)
                .build();
    }
}
