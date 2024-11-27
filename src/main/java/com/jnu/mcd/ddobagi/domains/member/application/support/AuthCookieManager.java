package com.jnu.mcd.ddobagi.domains.member.application.support;

import com.jnu.mcd.ddobagi.common.presentation.support.CookieManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class AuthCookieManager implements CookieManager {

    private final boolean HTTP_ONLY;
    private final boolean SECURE;
    private final String PATH;
    private final String SAME_SITE;
    private final String DOMAIN;
    private final int MAX_AGE;

    public AuthCookieManager(
            @Value("${token.cookie.path}") String PATH,
            @Value("${token.cookie.domain}") String DOMAIN,
            @Value("${token.cookie.sameSite}") String SAME_SITE,
            @Value("${token.cookie.httpOnly}") boolean HTTP_ONLY,
            @Value("${token.cookie.secure}") boolean SECURE,
            @Value("${token.cookie.maxAge}") int MAX_AGE) {
        this.DOMAIN = DOMAIN;
        this.PATH = PATH;
        this.SAME_SITE = SAME_SITE;
        this.HTTP_ONLY = HTTP_ONLY;
        this.SECURE = SECURE;
        this.MAX_AGE = MAX_AGE;
    }

    @Override
    public ResponseCookie setCookie(String key, String value) {
        return createCookie(key, value);
    }

    @Override
    public ResponseCookie deleteCookie(String key) {
        /** 구현 필요 */
        return null;
    }

    private ResponseCookie createCookie(String key, String value) {
        return ResponseCookie.from(key, value)
                .domain(DOMAIN)
                .httpOnly(HTTP_ONLY)
                .maxAge(MAX_AGE)
                .sameSite(SAME_SITE)
                .secure(SECURE)
                .path(PATH)
                .build();
    }
}
