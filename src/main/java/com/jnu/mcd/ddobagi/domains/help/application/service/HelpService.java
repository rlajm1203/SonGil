package com.jnu.mcd.ddobagi.domains.help.application.service;

import com.jnu.mcd.ddobagi.common.event.Events;
import com.jnu.mcd.ddobagi.common.interfaces.RequesterInfo;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpRequest;
import com.jnu.mcd.ddobagi.domains.help.application.dto.HelpResponse;
import com.jnu.mcd.ddobagi.domains.help.application.event.HelpMappingEvent;
import com.jnu.mcd.ddobagi.domains.help.persistence.Help;
import com.jnu.mcd.ddobagi.domains.help.persistence.HelpMapping;
import com.jnu.mcd.ddobagi.domains.help.persistence.HelpMapping.HelpMappingId;
import com.jnu.mcd.ddobagi.domains.help.persistence.HelpMappingRepository;
import com.jnu.mcd.ddobagi.domains.help.persistence.HelpRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpService {

    private final HelpRepository helpRepository;
    private final HelpMappingRepository helpMappingRepository;
    private final RequesterInfo requesterInfo;

    public HelpResponse getHelp(Long helpId){
        Help help = helpRepository.findById(helpId).orElse(null);

        if(help == null) throw new RuntimeException("Help not found");

        return HelpResponse.from(help);
    }

    public Long createHelp(HelpRequest request){
        Help help = Help.of(requesterInfo.getMemberId(), request);

        return helpRepository.save(help).getHelpId();
    }

    public Long match(Long helpId){
        Long memberId = requesterInfo.getMemberId();

        HelpMapping helpMapping = HelpMapping.builder()
                .id(new HelpMappingId(helpId, memberId))
                .build();

        try {
            helpMappingRepository.save(helpMapping);
        } catch (Exception e) {

        }

        Events.raise(new HelpMappingEvent(memberId, helpId));

        return helpId;
    }

    public List<Help> getHelpPage(int page, int size, String sortType){
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.asc("updatedDate"));

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(orders));

        return helpRepository.findAll(pageable).stream().toList();
    }
    


}
