package com.jnu.mcd.ddobagi.domains.member.application.service;

import com.jnu.mcd.ddobagi.common.interfaces.RequesterInfo;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberLoginRequest;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberSignUpRequest;
import com.jnu.mcd.ddobagi.domains.member.application.support.EncryptHelper;
import com.jnu.mcd.ddobagi.domains.member.application.support.token.TokenGenerator;
import com.jnu.mcd.ddobagi.domains.member.application.support.token.blacklist.BlackListTokenStore;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenModel;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenProvider;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenResolver;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.CreateMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.GetMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.LoginMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.LogoutMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.ReissueMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.persistence.Member;
import com.jnu.mcd.ddobagi.domains.member.persistence.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements
        CreateMemberUseCase,
        LoginMemberUseCase,
        ReissueMemberUseCase,
        LogoutMemberUseCase,
        GetMemberUseCase {

    private final MemberRepository memberRepository;
    private final RequesterInfo requesterInfo;
    private final TokenGenerator tokenGenerator;
    private final TokenResolver tokenResolver;
    private final TokenProvider tokenProvider;
    private final BlackListTokenStore blackListTokenStore;

    @Override
    public Long create(MemberSignUpRequest request) {
        Member member = Member.of(request);

        if(isExist(request.getId())) throw new RuntimeException("Member already exists");

        return memberRepository.save(member).getMemberId();
    }

    @Override
    public TokenModel login(MemberLoginRequest request) {
        String loginId = request.getId();
        String password = request.getPassword();

        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        if(!EncryptHelper.isMatch(password, member.getPassword())) throw new RuntimeException("Invalid password");

        return tokenGenerator.generate(member.getMemberId());
    }

    @Override
    public TokenModel reissue(String refreshToken){
        if(blackListTokenStore.isExistToken(refreshToken)) throw new RuntimeException("Invalid refresh token");

        Long memberId = tokenResolver.getUserDataByRefreshToken(refreshToken);

        return tokenGenerator.generate(memberId);
    }

    @Override
    public boolean logout(String refreshToken){
        blackListTokenStore.registerBlackListToken(refreshToken);

        return true;
    }

    private boolean isExist(String loginId) {
        List<Member> members = memberRepository.findAllAsc();
        
        return members.stream().map(Member::getLoginId)
                .anyMatch(id -> id.equals(loginId));
    }

    @Override
    public Member getMember() {
        Long memberId = requesterInfo.getMemberId();

        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("member Not Found"));

    }
}
