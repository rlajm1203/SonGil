package com.jnu.mcd.ddobagi.domains.help.application.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HelpCategory {
// 말동무, 운동 , 심부름
    CELANING("청소"),
    MEAL("식사"),
    PICK_UP("픽업"),
    WALK("산책"),
    LEARNING("학습"),
    BATH("목욕"),
    FRIEND("말동무"),
    EXERCISE("운동"),
    ERRAND("심부름");

    private String category;

    HelpCategory(String category) {
        this.category = category;
    }

    @JsonCreator
    public static HelpCategory fromString(String category) {
        for (HelpCategory helpCategory : HelpCategory.values()) {
            if (helpCategory.category.equals(category)) {
                return helpCategory;
            }
        }
        return null;
    }

}
