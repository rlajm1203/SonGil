package com.jnu.mcd.ddobagi.domains.member.application.usecase;


import com.jnu.mcd.ddobagi.domains.member.application.token.TokenModel;

public interface ReissueMemberUseCase {

    TokenModel reissue(String token );

}
