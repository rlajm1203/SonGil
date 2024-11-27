package com.jnu.mcd.ddobagi.domains.help.application.dto;

import java.util.Set;

public record HelpRequest(
        String helpType,
        Set<String> dayOfWeek,
        String address,
        String content,
        String title,
        Long price
) {
}
