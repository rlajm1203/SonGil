package com.jnu.mcd.ddobagi.domains.member.application.support.token.extractor;

import com.jnu.mcd.ddobagi.common.consts.StaticConst;
import com.jnu.mcd.ddobagi.domains.member.application.exception.NotFoundHeaderTokenException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("cookie")
@RequiredArgsConstructor
public class CookieTokenExtractor implements TokenExtractor {

    @Override
    public String extract(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            throw new NotFoundHeaderTokenException();
        }
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(StaticConst.RT_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(NotFoundHeaderTokenException::new);
    }
}
