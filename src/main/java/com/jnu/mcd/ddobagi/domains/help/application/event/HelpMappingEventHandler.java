package com.jnu.mcd.ddobagi.domains.help.application.event;


import com.jnu.mcd.ddobagi.domains.help.persistence.Help;
import com.jnu.mcd.ddobagi.domains.help.persistence.HelpRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HelpMappingEventHandler {
    
    private final HelpRepository helpRepository;
    private final EntityManager entityManager;

    @EventListener(HelpMappingEvent.class)
    @Transactional
    public void handle(HelpMappingEvent event){
        Help help = helpRepository.findById(event.getHelpId()).orElseThrow(() -> new RuntimeException("Help ID not found"));

        help.updateStatus("matching");
        entityManager.flush();
    }

}
