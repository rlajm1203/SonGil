package com.jnu.mcd.ddobagi.domains.member.application.support.token.blacklist;

public interface TokenStore {

    void registerBlackListToken(String key);

    boolean isExistToken(String token);

    void printTokenList();
}
