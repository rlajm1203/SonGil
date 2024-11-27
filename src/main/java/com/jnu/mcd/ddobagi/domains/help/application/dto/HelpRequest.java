package com.jnu.mcd.ddobagi.domains.help.application.dto;

import java.util.Set;

public record HelpRequest(
        String helpType,
        Set<String> dayOfWeek,
        String elderlyName,
        String phone,
        String birthDay,
        String address,
        String content
) {
}
