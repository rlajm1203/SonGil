package com.jnu.mcd.ddobagi.domains.help.application.dto;

import com.jnu.mcd.ddobagi.domains.help.application.model.HelpCategory;
import java.util.Set;

public record HelpRequest(
        String helpType,
        Set<String> dayOfWeek,
        String address,
        String content,
        String title,
        Long price,
        HelpCategory category
) {
}
