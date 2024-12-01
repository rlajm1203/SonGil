package com.jnu.mcd.ddobagi.domains.help.application.dto;

import com.jnu.mcd.ddobagi.domains.help.application.model.HelpCategory;
import com.jnu.mcd.ddobagi.domains.help.application.model.HelpType;
import com.jnu.mcd.ddobagi.domains.help.persistence.Help;

public record HelpResponse(
        String helpType,
        String helpCategory,
        String title,
        String content,
        String status,
        String address,
        Long price
) {

    public static HelpResponse from(Help help){
        return new HelpResponse(
                help.getHelpType().toString(),
                help.getCategory().toString(),
                help.getTitle(),
                help.getContent(),
                help.getHelpStatus().toString(),
                help.getAddress(),
                help.getPrice()
        );
    }

}
