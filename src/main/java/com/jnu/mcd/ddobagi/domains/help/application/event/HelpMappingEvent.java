package com.jnu.mcd.ddobagi.domains.help.application.event;

import com.jnu.mcd.ddobagi.common.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HelpMappingEvent implements Event {

    private Long memberId;
    private Long helpId;

}
