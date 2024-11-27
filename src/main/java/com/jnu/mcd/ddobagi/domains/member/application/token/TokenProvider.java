package com.jnu.mcd.ddobagi.domains.member.application.token;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenProvider {

    private final String MEMBER_ID_CLAIM_KEY = "memberId";
    private final SecretKey accessKey;
    private final SecretKey refreshKey;
    private final long accessExpirationTime;
    private final long refreshExpirationTime;

    public TokenProvider(
            @Value("${security.jwt.access.secretKey}") String accessKey,
            @Value("${security.jwt.refresh.secretKey}") String refreshKey,
            @Value("${security.jwt.access.validTime}") long accessExpirationTime,
            @Value("${security.jwt.refresh.validTime}") long refreshExpirationTime) {
        this.accessKey = Keys.hmacShaKeyFor(accessKey.getBytes(StandardCharsets.UTF_8));
        this.refreshKey = Keys.hmacShaKeyFor(refreshKey.getBytes(StandardCharsets.UTF_8));
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    public String createAccessToken(final Long memberId) {
        final Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .claim(MEMBER_ID_CLAIM_KEY, memberId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessExpirationTime))
                .signWith(accessKey)
                .compact();
    }

    public String createRefreshToken(final Long memberId) {
        final Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .claim(MEMBER_ID_CLAIM_KEY, memberId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshExpirationTime))
                .signWith(refreshKey)
                .compact();
    }
}
