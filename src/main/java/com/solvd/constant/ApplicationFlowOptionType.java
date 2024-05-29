package com.solvd.constant;


public enum ApplicationFlowOptionType {
    EXIT,
    CONTINUE;

    public static ApplicationFlowOptionType getSelectedOptionFromString(String option) {
        switch (option) {
            case "0":
                return EXIT;
            default:
                return CONTINUE;
        }
    }
}
