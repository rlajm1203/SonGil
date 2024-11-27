package com.jnu.mcd.ddobagi.domains.member.application.support.token;

import com.jnu.mcd.ddobagi.domains.member.application.token.TokenModel;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenModelConverter;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenProvider;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenGenerator {

    private final TokenProvider tokenProvider;
    private final TokenResolver tokenResolver;
    private final TokenModelConverter tokenModelConverter;

    public TokenModel generate(Long memberId) {

        String accessToken = tokenProvider.createAccessToken(memberId);
        String refreshToken = tokenProvider.createRefreshToken(memberId);

        return tokenModelConverter.from(
                accessToken, tokenResolver.getExpirationDateByAccessToken(accessToken),
                refreshToken, tokenResolver.getExpirationDateByRefreshToken(refreshToken));
    }

    public TokenModel reissue(String token) {

        String accessToken = tokenProvider.createAccessToken(getMemberId(token));
        String refreshToken = tokenProvider.createRefreshToken(getMemberId(token));

        return tokenModelConverter.from(
                accessToken, tokenResolver.getExpirationDateByAccessToken(accessToken),
                refreshToken, tokenResolver.getExpirationDateByRefreshToken(refreshToken));
    }

    private Long getMemberId(String token) {
        return tokenResolver.getUserDataByRefreshToken(token);
    }
}
