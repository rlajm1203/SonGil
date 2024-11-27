package com.jnu.mcd.ddobagi.domains.member.presentation;

import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponse;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseBody.SuccessBody;
import com.jnu.mcd.ddobagi.common.presentation.response.ApiResponseGenerator;
import com.jnu.mcd.ddobagi.common.presentation.response.MessageCode;
import com.jnu.mcd.ddobagi.common.presentation.support.CookieManager;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberAccessTokenResponse;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberLoginRequest;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberMeResponse;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberSignUpRequest;
import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberSignUpResponse;
import com.jnu.mcd.ddobagi.domains.member.application.support.token.extractor.TokenExtractor;
import com.jnu.mcd.ddobagi.domains.member.application.token.TokenModel;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.CreateMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.GetMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.LoginMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.LogoutMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.application.usecase.ReissueMemberUseCase;
import com.jnu.mcd.ddobagi.domains.member.persistence.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final CreateMemberUseCase createMemberUseCase;
    private final LoginMemberUseCase loginUseCase;
    private final LogoutMemberUseCase logoutUseCase;
    private final ReissueMemberUseCase reissueUseCase;
    private final GetMemberUseCase getMemberUseCase;
    private final TokenExtractor tokenExtractor;
    private final CookieManager cookieManager;

    public MemberController(CreateMemberUseCase createMemberUseCase,
                            LoginMemberUseCase loginUseCase,
                            LogoutMemberUseCase logoutUseCase,
                            ReissueMemberUseCase reissueMemberUseCase,
                            GetMemberUseCase getMemberUseCase,
                            @Qualifier("cookie")
                            TokenExtractor tokenExtractor,
                            CookieManager cookieManager) {

        this.createMemberUseCase = createMemberUseCase;
        this.loginUseCase = loginUseCase;
        this.logoutUseCase = logoutUseCase;
        this.reissueUseCase = reissueMemberUseCase;
        this.tokenExtractor = tokenExtractor;
        this.cookieManager = cookieManager;
        this.getMemberUseCase = getMemberUseCase;
    }


    @PostMapping("/auth/login")
    public ApiResponse<SuccessBody<MemberAccessTokenResponse>> login(@RequestBody MemberLoginRequest request, HttpServletResponse response) {
        TokenModel token = loginUseCase.login( request );

        ResponseCookie cookie = cookieManager.setCookie("refreshToken", token.getRefreshToken());

        response.addCookie(new Cookie(cookie.getName(), cookie.getValue()));

        return ApiResponseGenerator.success(new MemberAccessTokenResponse(token.getAccessToken(), token.getAccessExpiredTime()),
                HttpStatus.OK, MessageCode.LOGIN);
    }

    @PostMapping("/auth/dupl-check")
    public ApiResponse<SuccessBody<String>> duplicationCheck(
            @RequestBody String loginId
    ) {
        createMemberUseCase.duplicationCheck( loginId );
        
        return ApiResponseGenerator.success("사용가능한 아이디 입니다.", HttpStatus.OK, MessageCode.GET);
    }

    @PostMapping("/auth/signup")
    public ApiResponse<SuccessBody<MemberSignUpResponse>> signup(@RequestBody MemberSignUpRequest request) {
        Long memberId = createMemberUseCase.create( request );

        return ApiResponseGenerator.success(new MemberSignUpResponse(memberId), HttpStatus.OK, MessageCode.CREATE);
    }

    @PostMapping("/auth/reissue")
    public ApiResponse<SuccessBody<MemberAccessTokenResponse>> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = tokenExtractor.extract( request );

        TokenModel token = reissueUseCase.reissue( refreshToken );

        ResponseCookie cookie = cookieManager.setCookie("refreshToken", token.getRefreshToken());
        response.addCookie(new Cookie(cookie.getName(), cookie.getValue()));

        return ApiResponseGenerator.success(new MemberAccessTokenResponse(token.getAccessToken(), token.getAccessExpiredTime()),
                HttpStatus.OK, MessageCode.LOGIN);
    }

    @GetMapping("/member/me")
    public ApiResponse<SuccessBody<MemberMeResponse>> getMyInfo() {
        Member member = getMemberUseCase.getMember();

        return ApiResponseGenerator.success(MemberMeResponse.fromMember(member),
                HttpStatus.OK,
                MessageCode.GET);
    }
}
