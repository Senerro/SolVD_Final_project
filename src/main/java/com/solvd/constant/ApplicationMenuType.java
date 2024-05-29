package com.solvd.constant;

public enum ApplicationMenuType {
    MAIN_MENU,
    AVAILABLE_CITIES_MENU,
    BUILD_ROUTE_MENU;

    public static ApplicationMenuType getApplicationMenuType(String option) {
        switch (option) {
            case "1":
                return AVAILABLE_CITIES_MENU;
            case "2":
                return BUILD_ROUTE_MENU;
            default:
                return MAIN_MENU;
        }
    }
}
