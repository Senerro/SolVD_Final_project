package com.solvd.constant;

import lombok.Getter;

public enum InputValidationRegexType {
    MAIN_MENU_OPTIONS_VALIDATION("[0-2]"),
    SUBMENU_OPTIONS_VALIDATION("[0-1]"),
    BUILD_ROUTE_MENU_VALIDATION("[0-3]"),
    BUILD_ROUTE_WITH_STOPS_MENU_VALIDATION("[0-1]"),
    INPUT_CITY_VALIDATION("(?i)[a-z]{4,12}");

    InputValidationRegexType(String regex) {
        this.regex = regex;
    }

    @Getter
    private final String regex;
}
