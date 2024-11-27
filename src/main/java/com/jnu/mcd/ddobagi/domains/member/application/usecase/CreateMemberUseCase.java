package com.jnu.mcd.ddobagi.domains.member.application.usecase;


import com.jnu.mcd.ddobagi.domains.member.application.dto.MemberSignUpRequest;

public interface CreateMemberUseCase {

    Long create(MemberSignUpRequest request);

    void duplicationCheck(String id);

}
