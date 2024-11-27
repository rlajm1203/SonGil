package com.jnu.mcd.ddobagi.domains.member.application.dto;

import com.jnu.mcd.ddobagi.common.support.dto.AbstractResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberAccessTokenResponse implements AbstractResponseDto {

    private String accessToken;
    private Long accessExpiredTime;
}
