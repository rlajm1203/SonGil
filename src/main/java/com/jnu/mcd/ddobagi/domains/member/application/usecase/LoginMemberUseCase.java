package com.jnu.mcd.ddobagi.domains.member.application.usecase;


import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberLoginRequest;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenModel;

public interface LoginMemberUseCase {

    TokenModel login(MemberLoginRequest request);

}
