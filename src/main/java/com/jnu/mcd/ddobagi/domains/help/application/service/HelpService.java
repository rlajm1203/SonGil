package com.jnu.mcd.ddobagi.domains.help.application.service;

import com.jnu.mcd.ddobagi.common.interfaces.RequesterInfo;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpRequest;
import com.jnu.mcd.ddobagi.domains.help.application.dto.OnceHelpResponse;
import com.jnu.mcd.ddobagi.domains.help.persistence.Help;
import com.jnu.mcd.ddobagi.domains.help.persistence.HelpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpService {

    private final HelpRepository helpRepository;
    private final RequesterInfo requesterInfo;

    public OnceHelpResponse getHelp(Long helpId){
        Help help = helpRepository.findById(helpId).orElse(null);

        if(help == null) throw new RuntimeException("Help not found");

        return OnceHelpResponse.from(help);
    }

    public Long createHelp(HelpRequest request){
        Help help = Help.of(requesterInfo.getMemberId(), request);

        return helpRepository.save(help).getHelpId();
    }
    
    

}
