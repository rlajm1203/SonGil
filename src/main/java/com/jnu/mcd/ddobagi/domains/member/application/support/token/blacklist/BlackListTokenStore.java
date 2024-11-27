package com.jnu.mcd.ddobagi.domains.member.application.support.token.blacklist;


import com.jnu.mcd.ddobagi.domains.member.application.token.TokenResolver;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BlackListTokenStore implements TokenStore {

    private final Map<String, String> tokenMap = new HashMap<>();
    private final TokenResolver tokenResolver;

    @Override
    public void registerBlackListToken(String token) {
        /** 블랙리스트 토큰을 등록할 때, 부가적인 로직이 필요할까? */
        String memberId = tokenResolver.getUserDataByRefreshToken(token).toString();
        tokenMap.put(token, memberId);
    }

    @Override
    public boolean isExistToken(String token) {
        return tokenMap.containsKey(token);
    }

    @Override
    public void printTokenList() {
        log.info("현재까지 등록된 토큰 리스트");
        tokenMap.keySet().forEach(log::info);
    }
}
