package com.jnu.mcd.ddobagi.common.util;

import com.jnu.mcd.ddobagi.common.interfaces.RequesterInfo;
import org.springframework.stereotype.Component;

@Component
public class AuthInfo implements RequesterInfo {

    private final ThreadLocal<Long> memberId = new ThreadLocal<>();

    @Override
    public Long getMemberId() {
        return this.memberId.get();
    }

    public void setMemberId( Long memberId ) {
        this.memberId.set( memberId );
    }

    public void clear(){
        this.memberId.remove();
    }
}
