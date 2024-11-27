package com.jnu.mcd.ddobagi.common.config.interceptor;

import com.starlight.moonshield.common.utils.AuthInfo;
import com.starlight.moonshield.domains.member.application.support.token.extractor.TokenExtractor;
import com.starlight.moonshield.domains.member.application.token.TokenResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInfoInterceptor implements HandlerInterceptor {
    
    private final TokenResolver tokenResolver;
    private final TokenExtractor tokenExtractor;
    private final AuthInfo authInfo;

    public AuthInfoInterceptor( TokenResolver tokenResolver,
                                @Qualifier("header")
                                TokenExtractor tokenExtractor,
                                AuthInfo authInfo ) {
        this.tokenResolver = tokenResolver;
        this.tokenExtractor = tokenExtractor;
        this.authInfo = authInfo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        String token = tokenExtractor.extract( request );
        Long memberId = tokenResolver.getUserDataByAccessToken(token);

        this.authInfo.setMemberId( memberId );
        
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        this.authInfo.clear();

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
