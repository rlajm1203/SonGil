package com.jnu.mcd.ddobagi.domains.member.application.support.token.extractor;

import com.jnu.mcd.ddobagi.domains.member.application.exception.NotFoundHeaderTokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component("header")
@RequiredArgsConstructor
public class HeaderTokenExtractor implements TokenExtractor {

    @Override
    public String extract(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) throw new NotFoundHeaderTokenException();
        header = header.replace("Bearer ", "");
        return header;
    }
}
