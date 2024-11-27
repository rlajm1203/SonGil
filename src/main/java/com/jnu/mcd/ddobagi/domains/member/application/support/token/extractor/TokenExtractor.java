package com.jnu.mcd.ddobagi.domains.member.application.support.token.extractor;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenExtractor {

    String extract(HttpServletRequest request);
}
