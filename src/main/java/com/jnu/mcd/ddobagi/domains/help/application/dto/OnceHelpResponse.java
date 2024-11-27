package com.jnu.mcd.ddobagi.domains.help.application.dto;

import com.jnu.mcd.ddobagi.domains.help.persistence.Help;

public record OnceHelpResponse(
        String title,
        String content,
        String status,
        String address,
        Long price
) {

    public static OnceHelpResponse from(Help help){
        return new OnceHelpResponse(
                help.getTitle(),
                help.getContent(),
                help.getHelpStatus().toString(),
                help.getAddress(),
                help.getPrice()
        );
    }

}
